package com.offsidegaming.measuremonitoringservice.utils;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.lang.reflect.Type;

/**
 * Генератор данных для тестов.
 */
public class PodamUtils {
    private static final PodamFactory PODAM_FACTORY;

    static {
        PODAM_FACTORY = new PodamFactoryImpl();
    }

    /**
     * Генерирует Pojo объект заданного типа.
     *
     * @param <T>             тип генерируемого объекта
     * @param type            объект {@link Class} генерируемого объекта
     * @param genericTypeArgs обобщённые типы генерируемого объекта
     * @return объект - результат генерации
     */
    public static <T> T manufacturePojo(Class<T> type, Type... genericTypeArgs) {
        return PODAM_FACTORY.manufacturePojo(type, genericTypeArgs);
    }
}
