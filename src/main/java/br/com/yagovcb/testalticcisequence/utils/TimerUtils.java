package br.com.yagovcb.testalticcisequence.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;

@Slf4j
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TimerUtils {

    private static final LocalTime referenceStarterTime = LocalTime.now();


    public static void timeBreakPrint(String msg) {
        log.info(String.format(">>> %s - %s",
                Optional.ofNullable(msg).orElse("EXEC."),
                timeBreakFormat())
        );
    }

    public static String timeBreakFormat() {
        return formatDuration(timeBreak());
    }

    private static Duration timeBreak() {
        return Duration.between(referenceStarterTime, LocalTime.now());
    }

    private static String formatDuration(Duration dur) {
        return String.format("Processing time: %d s %03d ms %d ns",
                dur.toSeconds(),
                dur.toMillisPart(),
                dur.toNanosPart());
    }
}
