package car;

import org.junit.platform.commons.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cars {
    private final List<Car> carList;
    private static final int CAR_NAME_LIMIT = 5;
    private static final String INVALID_NAME_LENGTH = "이름은 5자 이하만 가능하다.";
    private static final String SPLIT_DELIMITER = ",";
    private static final String MUST_NOT_BLANK = "공백 입력은 불가능하다.";

    public Cars(String userInput) {
        this.carList = this.initByStringInput(userInput);
    }

    private List<Car> initByStringInput(String userInput) {
        String[] carNames = userInput.split(SPLIT_DELIMITER);
        return Arrays.stream(carNames)
                .map(String::trim)
                .map(carName -> {
                    validateInput(carName);
                    return new Car(carName);
                })
                .toList();
    }

    private void validateInput(String userInput){
        this.validateNotBlank(userInput);
        this.validateLength(userInput);
    }
    private void validateNotBlank(String userInput){
        if (StringUtils.isBlank(userInput)) {
            throw new IllegalArgumentException(MUST_NOT_BLANK);
        }
    }
    private void validateLength(String userInput) {
        if (userInput.length() > CAR_NAME_LIMIT) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH);
        }
    }

    public List<Car> getCarList() {
        return new ArrayList<>(carList);
    }

    public List<String> getCarNames() {
        return carList.stream().map(Car::getName).toList();
    }

    public List<String> getWinnersNames() {
        int maxLocation = carList.stream()
                .mapToInt(Car::getLocation)
                .max()
                .orElse(0);
        return carList.stream()
                .filter(car -> car.getLocation() == maxLocation)
                .map(Car::getName)
                .toList();
    }
    public void move() {
        this.carList.forEach(Car::move);
    }
}
