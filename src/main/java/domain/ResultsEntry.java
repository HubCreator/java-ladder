package domain;

import java.util.List;
import java.util.stream.Collectors;

public class ResultsEntry {

    private final List<Result> results;

    public ResultsEntry(List<String> results) {
        this.results = results.stream()
                .map(Result::new)
                .collect(Collectors.toList());
    }

    private void validateColumn(Column column) {
        if (column.get() >= results.size()) {
            throw new IllegalArgumentException("유효 범위를 초과한 column입니다.");
        }
    }

    public Result getResultByColumn(Column column) {
        validateColumn(column);
        return results.get(column.get());
    }

    public List<Result> getResults() {
        return results.stream()
                .map(Result::new)
                .collect(Collectors.toUnmodifiableList());
    }
}
