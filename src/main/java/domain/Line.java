package domain;

import java.util.List;

public class Line {

    private final List<Boolean> movements;

    public Line(List<Boolean> movements) {
        validate(movements);
        this.movements = movements;
    }

    private void validate(List<Boolean> movements) {
        int initialIndex = 0;
        Boolean previousMovement = movements.get(initialIndex);

        for (int i = initialIndex + 1; i < movements.size(); i++) {
            validateSequential(previousMovement, movements.get(i));
            previousMovement = movements.get(i);
        }
    }

    private void validateSequential(Boolean previousMovement, Boolean currentMovement) {
        if (previousMovement & currentMovement) {
            throw new IllegalArgumentException("가로라인이 연속될 수 없습니다.");
        }
    }

    public void move(Position position) {
        int column = position.getColumn();

        if (column != 0 && movements.get(column - 1)) {
            position.goLeft();
        }
        if (column != movements.size() && movements.get(column)) {
            position.goRight();
        }
    }

    public int size() {
        return movements.size();
    }

    public List<Boolean> getMovements() {
        return movements;
    }
}
