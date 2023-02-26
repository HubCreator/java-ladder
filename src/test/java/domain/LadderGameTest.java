package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LadderGameTest {

    LadderGame ladderGame;

    @BeforeEach
    void init() {
        List<Line> customizedLines = List.of(
                new Line(List.of(TRUE, FALSE, TRUE)),
                new Line(List.of(FALSE, TRUE, FALSE)),
                new Line(List.of(TRUE, FALSE, FALSE)),
                new Line(List.of(FALSE, TRUE, FALSE)),
                new Line(List.of(TRUE, FALSE, TRUE))
        );
        List<String> resultList = List.of("꽝", "5000", "꽝", "3000");
        People people = new People(List.of("pobi", "honux", "crong", "jk"));
        Results results = new Results(resultList, people);
        ladderGame = new LadderGame(people, results, new Ladder(customizedLines));
    }

    @DisplayName("단일 사용자의 결과 출력")
    @ParameterizedTest
    @CsvSource({
            "pobi,꽝",
            "honux,3000",
            "crong,꽝",
            "jk,5000",
    })
    void single_result_test(String name, String result) {
        assertThat(ladderGame.calculateResult(new Person(name)))
                .isEqualTo(new Result(result));
    }

    @DisplayName("모든 사용자의 결과 출력")
    @Test
    void all_result_test() {
        Map<Person, Result> data = Map.ofEntries(
                Map.entry(new Person("pobi"), new Result("꽝")),
                Map.entry(new Person("honux"), new Result("3000")),
                Map.entry(new Person("crong"), new Result("꽝")),
                Map.entry(new Person("jk"), new Result("5000"))
        );
        ResultsMap resultMap = ladderGame.getTotalResults();
        assertThat(resultMap.getResultMap()).isEqualTo(data);
    }
}
