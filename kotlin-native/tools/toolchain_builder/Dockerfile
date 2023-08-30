# We might want to switch to alpine, but it is not stable enough yet.
FROM ubuntu:23.04

ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Install crosstool-ng deps.
RUN apt-get update
RUN apt-get install -y curl gcc git g++ gperf bison flex texinfo help2man make libncurses5-dev \
    python3-dev autoconf automake libtool gawk wget bzip2 xz-utils unzip \
    patch libstdc++6 rsync libtool-bin

# Put a fix for strip.
COPY patches/github_pull_1244.patch .
# Install crosstool-ng.
RUN rm -rf crosstool-ng
RUN git clone --branch crosstool-ng-1.25.0 --depth 1 https://github.com/crosstool-ng/crosstool-ng.git
RUN cd crosstool-ng && ./bootstrap
#    git checkout b2151f1dba2b20c310adfe7198e461ec4469172b && \
#    git apply ../github_pull_1244.patch && \
RUN cd crosstool-ng && ls -la
RUN cd crosstool-ng && ./configure
RUN cd crosstool-ng && make
RUN cd crosstool-ng && make install
RUN rm -rf crosstool-ng

# Create a user.
ARG USERNAME=ct
RUN groupadd -g 1001 $USERNAME
RUN useradd -r -u 1001 --create-home -g $USERNAME $USERNAME
USER $USERNAME
WORKDIR /home/$USERNAME

# Download zlib sources.
RUN curl -LO https://zlib.net/zlib-1.3.tar.gz && \
    tar -xf zlib-1.3.tar.gz && \
    rm zlib-1.3.tar.gz

# Save crosstool-ng config files.
COPY toolchains toolchains

# Used by crosstool-ng.
RUN mkdir -p ~/src
RUN wget -P ~/src https://libisl.sourceforge.io/isl-0.20.tar.bz2
RUN wget -P ~/src https://github.com/libexpat/libexpat/releases/download/R_2_2_6/expat-2.2.6.tar.bz2
RUN wget -P ~/src https://www.mirrorservice.org/sites/ftp.kernel.org/pub/linux/kernel/v6.x/linux-6.2.15.tar.xz

ENV TARGET=x86_64-unknown-linux-gnu
ENV VERSION=gcc-8.3.0-glibc-2.19-kernel-4.9
ENV TOOLCHAIN_VERSION_SUFFIX=""

# Add entry point.
COPY build_toolchain.sh .
ENTRYPOINT "/bin/bash"
#ENTRYPOINT "/bin/bash" "build_toolchain.sh" ${TARGET} ${VERSION} ${TOOLCHAIN_VERSION_SUFFIX}

CMD echo "End Docker" > /artifacts/docker.log
CMD find . -name "build.log" -exec cat {} \;
