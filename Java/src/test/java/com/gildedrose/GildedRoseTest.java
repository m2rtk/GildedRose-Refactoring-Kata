package com.gildedrose;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @ParameterizedTest
    @MethodSource("allTestCases")
    void givenNormalItem_then(TestCase test) {
        final String name = test.name;
        final List<int[]> data = test.data;
        final Item item = new Item(name, data.get(0)[0], data.get(0)[1]);

        final GildedRose gildedRose = new GildedRose(new Item[]{item});

        for (int[] dayData : data) {
            System.out.println(Arrays.toString(dayData));
            assertEquals(dayData[0], item.sellIn);
            assertEquals(dayData[1], item.quality);
            gildedRose.updateQuality();
        }
    }

    private static Stream<TestCase> allTestCases() throws IOException {
        final Path path = Paths.get("src", "test", "resources", "test_cases");

        return Files.walk(path)
                .filter(Files::isRegularFile)
                .map(GildedRoseTest::readAllLines)
                .map(TestCase::new);
    }

    private static List<String> readAllLines(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class TestCase {
        private final String name;
        private final List<int[]> data;

        private TestCase(List<String> lines) {
            this.name = lines.get(0).trim();
            this.data = lines.subList(1, lines.size()).stream()
                    .map(TestCase::readIntPair)
                    .collect(Collectors.toList());
        }

        private static int[] readIntPair(String line) {
            final String[] split = line.split("\\s*,\\s*");
            return new int[] {Integer.parseInt(split[0]), Integer.parseInt(split[1])};
        }

        @Override
        public String toString() {
            return name + " " + Arrays.toString(data.get(0));
        }
    }
}
