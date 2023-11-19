package edu.project3;

import java.util.Map;

public class Format {
    private final static String STAT_1 = "info";
    private final static String STAT_2 = "sources";
    private final static String STAT_3 = "responses";

    public enum FORMAT {
        MARKDOWN(Map.of(
            STAT_1, """
                #### Общая информация

                |        Метрика        |     Значение |
                |:---------------------:|-------------:|
                |       Файл(-ы)        |       %s     |
                |    Начальная дата     |       %s     |
                |     Конечная дата     |       %s     |
                |  Количество запросов  |       %s     |
                | Средний размер ответа |       %s     |
                """,
            STAT_2, """

                #### Запрашиваемые ресурсы

                |     Ресурс      | Количество |
                |:---------------:|-----------:|
                """,
            STAT_3, """

                #### Коды ответа

                | Код |          Имя          | Количество |
                |:---:|:---------------------:|:----------:|
                """
        )),
        ADOC(Map.of(
            STAT_1, """
                == Общая информация

                [cols="^,>",options="header",]
                |===
                | Метрика                | Значение
                | Файл(-ы)               | %s
                | Начальная дата         | %s
                | Конечная дата          | %s
                | Количество запросов    | %s
                | Средний размер ответа  | %s
                |===

                """,
            STAT_2, """
                == Запрашиваемые ресурсы

                [cols="^,>",options="header",]
                |===
                | Ресурс                | Количество
                """,
            STAT_3, """
                == Коды ответа

                [cols="^,^,^",options="header",]
                |===
                | Код                | Имя      |  Количество
                """
        ));

        FORMAT(Map<String, String> format) {
            this.format = format;
        }

        private final Map<String, String> format;

        Map<String, String> getFormat() {
            return format;
        }
    }
}
