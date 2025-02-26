package com.sibirajen.unitConvBcknd.service;

import com.sibirajen.unitConvBcknd.exceptions.UnitConversionException;
import com.sibirajen.unitConvBcknd.interFaces.ConvertFunction;
import com.sibirajen.unitConvBcknd.model.Request;
import com.sibirajen.unitConvBcknd.model.enums.Length;
import com.sibirajen.unitConvBcknd.model.enums.Temperature;
import com.sibirajen.unitConvBcknd.model.enums.Type;
import com.sibirajen.unitConvBcknd.model.enums.Weight;
import org.springframework.stereotype.Service;

@Service
public class UnitConvertor {

    public static double safeConvert(ConvertFunction convertFunction, String methodName){
        try {
            return convertFunction.convert();
        } catch (IllegalArgumentException e) {
            throw new UnitConversionException("Invalid unit or value in " + methodName + ": " + e.getMessage());
        }
    }

    public static double getResult(Request request) {
        return safeConvert( () -> {
            if (request == null
                    || request.getFromUnit() == null
                    || request.getToUnit() == null
                    || request.getType() == null) {
                throw new IllegalArgumentException("Invalid request: null values detected");
            }
            double val = request.getValue();
            String fromUnit = request.getFromUnit().toUpperCase();
            String toUnit = request.getToUnit().toUpperCase();
            Type type = request.getType();

            return switch (type) {
                case LENGTH -> getLength(val, fromUnit, toUnit);
                case WEIGHT -> getWeight(val, fromUnit, toUnit);
                case TEMPERATURE -> getTemperature(val, fromUnit, toUnit);
            };
        }, "getResult");
    }

    private static double getTemperature(double val, String fromUnit, String toUnit) {
        return safeConvert( () -> {
            Temperature fromType = Temperature.valueOf(fromUnit);
            Temperature toType = Temperature.valueOf(toUnit);
            return switch (fromType) {
                case KELVIN -> switch (toType) {
                    case CELSIUS -> val - 273.15;
                    case FAHRENHEIT -> (val - 273.15) * 9.0 / 5.0 + 32;
                    case KELVIN -> val;
                };
                case CELSIUS -> switch (toType) {
                    case KELVIN -> val + 273.15;
                    case CELSIUS -> val;
                    case FAHRENHEIT -> val * (9.0 / 5.0) + 32;
                };
                case FAHRENHEIT -> switch (toType) {
                    case FAHRENHEIT -> val;
                    case KELVIN -> (val - 32) * 5.0 / 9.0 + 273.15;
                    case CELSIUS -> (val - 32) * 5.0 / 9.0;
                };
            };
        }, "getTemperature");
    }


    private static double getLength(double val,String fromUnit, String toUnit){
        return safeConvert( () -> {
            Length fromType = Length.valueOf(fromUnit);
            Length toType = Length.valueOf(toUnit);
            if (fromType.equals(toType)) {
                return val;
            }
            double from = fromType.getVal();
            double to = toType.getVal();
            return val * from/to;
        }, "getLength");
    }

    private static double getWeight(double val, String fromUnit, String toUnit){
        return safeConvert( () -> {
            Weight fromType = Weight.valueOf(fromUnit);
            Weight toType = Weight.valueOf(toUnit);
            if (fromType.equals(toType)) {
                return val;
            }
            double from = fromType.getVal();
            double to = toType.getVal();
            return val * from/to;
        }, "getWeight");
    }
}
