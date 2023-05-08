interface Flow<T>

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, R> combine(
    flow: Flow<T1>,
    flow2: Flow<T2>,
    flow3: Flow<T3>,
    flow4: Flow<T4>,
    flow5: Flow<T5>,
    flow6: Flow<T6>,
    flow7: Flow<T7>,
    flow8: Flow<T8>,
    flow9: Flow<T9>,
    flow10: Flow<T10>,
    flow11: Flow<T11>,
    flow12: Flow<T12>,
    flow13: Flow<T13>,
    flow14: Flow<T14>,
    flow15: Flow<T15>,
    flow16: Flow<T16>,
    flow17: Flow<T17>,
    flow18: Flow<T18>,
    flow19: Flow<T19>,
    flow20: Flow<T20>,
    flow21: Flow<T21>,
    flow22: Flow<T22>,
    flow23: Flow<T23>,
    transform: suspend (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23) -> R
): Flow<R> = combine(flow, flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11, flow12, flow13, flow14, flow15, flow16, flow17, flow18, flow19, flow20, flow21, flow22, flow23) { args: Array<*> ->
    transform(
        args[0] as T1,
        args[1] as T2,
        args[2] as T3,
        args[3] as T4,
        args[4] as T5,
        args[5] as T6,
        args[6] as T7,
        args[7] as T8,
        args[8] as T9,
        args[9] as T10,
        args[10] as T11,
        args[11] as T12,
        args[12] as T13,
        args[13] as T14,
        args[14] as T15,
        args[15] as T16,
        args[16] as T17,
        args[17] as T18,
        args[18] as T19,
        args[19] as T20,
        args[20] as T21,
        args[21] as T22,
        args[22] as T23,
    )
}
