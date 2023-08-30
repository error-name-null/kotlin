#!/bin/bash

set -eou pipefail

TARGET=$1
VERSION=$2
TOOLCHAIN_VERSION_SUFFIX=$3
HOME=/home/ct
ZLIB_VERSION=1.3

build_toolchain() {
  echo "Start" > /artifacts/fred.txt
  ls -l $HOME
  rm -rf $HOME/build-"$TARGET"
  mkdir $HOME/build-"$TARGET"
  cd $HOME/build-"$TARGET"
  cp $HOME/toolchains/"$TARGET"/"$VERSION".config .config
  echo "Before" >> /artifacts/fred.txt
  ct-ng build
  echo "After" >> /artifacts/fred.txt
  find . -name "build.log" -exec cp {} /artifacts/ \;
  cd ..
  echo "End" >> /artifacts/fred.txt
}

build_zlib() {
  TOOLCHAINS_PATH=$HOME/x-tools
  INSTALL_PATH=$TOOLCHAINS_PATH/$TARGET/$TARGET/sysroot/usr
  TOOLCHAIN_BIN_PREFIX=$TOOLCHAINS_PATH/$TARGET/bin/$TARGET
  cd $HOME/zlib-$ZLIB_VERSION

  CHOST=$TARGET \
  CC=$TOOLCHAIN_BIN_PREFIX-gcc \
  AR=$TOOLCHAIN_BIN_PREFIX-ar \
  RANLIB=$TOOLCHAIN_BIN_PREFIX-ranlib \
  ./configure \
  --prefix="$INSTALL_PATH"

  make
  chmod u+w /home/ct/x-tools/aarch64-unknown-linux-gnu/aarch64-unknown-linux-gnu/sysroot/usr/share
  chmod u+w /home/ct/x-tools/aarch64-unknown-linux-gnu/aarch64-unknown-linux-gnu/sysroot/usr/lib
  chmod u+w /home/ct/x-tools/aarch64-unknown-linux-gnu/aarch64-unknown-linux-gnu/sysroot/usr/include
  make install
}

build_archive() {
  cd $HOME/x-tools
  if [ -z "$TOOLCHAIN_VERSION_SUFFIX" ]
  then
    FULL_NAME="$TARGET-$VERSION"
  else
    FULL_NAME="$TARGET-$VERSION-$TOOLCHAIN_VERSION_SUFFIX"
  fi
  mv "$TARGET" "$FULL_NAME"
  ARCHIVE_NAME="$FULL_NAME.tar.gz"
  tar -czvf "$ARCHIVE_NAME" "$FULL_NAME"
  cp "$ARCHIVE_NAME" /artifacts/"$ARCHIVE_NAME"
}

echo "building toolchain for $TARGET"

build_toolchain
build_zlib
build_archive

/bin/bash

#find . -name "build.log" -exec cp {} /artifacts/ \;
#echo "Steve2" > /artifacts/fred.txt
