# flink-connector-jdbc
> Java library provides [Apache Flink](https://flink.apache.org/) connector sink for JDBC database that can be used with Flink 1.8 runtime version.
Connector code is backported from the latest Flink version (1.11) in order to be used in [Amazon Kinesis Data Analytics](https://aws.amazon.com/kinesis/data-analytics/) applications.

[![Build Status][ci-image]][ci-url]
[![License][license-image]][license-url]
[![Developed at Klarna][klarna-image]][klarna-url]

At Klarna we use streaming applications extensively. Amazon Kinesis Data Analytics with Flink 1.8 is starting to be one of the choices for the development of new streaming analytics applications at Klarna. Unfortunately, some of the latest features developed in the Apache Flink project version after 1.8 are not available yet in Amazon Kinesis Data Analytics.

`flink-connector-jdbc-1.8` is a Java library that contains code backported from the latest Flink version (1.11) `flink-connector-jdbc` library that can be used in Amazon Kinesis Data Analytics / Flink 1.8.

## Usage example

```java
import com.klarna.org.apache.flink.api.java.io.jdbc.JDBCOptions;
import com.klarna.org.apache.flink.api.java.io.jdbc.JDBCUpsertOutputFormat;
import com.klarna.org.apache.flink.api.java.io.jdbc.JDBCUpsertSinkFunction;

...
env.addSource(createConsumer())
   .addSink(new JDBCUpsertSinkFunction(JDBCUpsertOutputFormat.builder()
            .setFieldNames(new String[]{
                    "event_id",
                    "created_at"
            })
            .setFieldTypes(new int[]{
                    Types.VARCHAR,
                    Types.TIMESTAMP
            })
            .setFlushIntervalMills(10000)
            .setFlushMaxSize(5000)
            .setKeyFields(new String[]{ "event_id" })
            .setMaxRetryTimes(3)
            .setOptions(JDBCOptions.builder()
                    .setDBUrl(dbUrl)
                    .setDriverName(Driver.class.getName())
                    .setUsername(dbUsername)
                    .setPassword(dbPassword)
                    .setTableName(tableName)
                    .build())
            .build()));
```

## Development setup

This project uses [Maven](https://maven.apache.org/) to set up the development environment. The recommended workflow to build and install the library is the following.

```sh
mvn clean install
```

## How to contribute

See our guide on [contributing](.github/CONTRIBUTING.md).

## Release History

See our [changelog](CHANGELOG.md).

## License

Copyright © 2020 Klarna Bank AB

For license details, see the [LICENSE](LICENSE) file in the root of this project.


<!-- Markdown link & img dfn's -->
[ci-image]: https://img.shields.io/badge/build-passing-brightgreen?style=flat-square
[ci-url]: https://github.com/klarna-incubator/TODO
[license-image]: https://img.shields.io/badge/license-Apache%202-blue?style=flat-square
[license-url]: http://www.apache.org/licenses/LICENSE-2.0
[klarna-image]: https://img.shields.io/badge/%20-Developed%20at%20Klarna-black?labelColor=ffb3c7&style=flat-square&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAOCAYAAAAmL5yKAAAAAXNSR0IArs4c6QAAAIRlWElmTU0AKgAAAAgABQESAAMAAAABAAEAAAEaAAUAAAABAAAASgEbAAUAAAABAAAAUgEoAAMAAAABAAIAAIdpAAQAAAABAAAAWgAAAAAAAALQAAAAAQAAAtAAAAABAAOgAQADAAAAAQABAACgAgAEAAAAAQAAABCgAwAEAAAAAQAAAA4AAAAA0LMKiwAAAAlwSFlzAABuugAAbroB1t6xFwAAAVlpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IlhNUCBDb3JlIDUuNC4wIj4KICAgPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4KICAgICAgPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIKICAgICAgICAgICAgeG1sbnM6dGlmZj0iaHR0cDovL25zLmFkb2JlLmNvbS90aWZmLzEuMC8iPgogICAgICAgICA8dGlmZjpPcmllbnRhdGlvbj4xPC90aWZmOk9yaWVudGF0aW9uPgogICAgICA8L3JkZjpEZXNjcmlwdGlvbj4KICAgPC9yZGY6UkRGPgo8L3g6eG1wbWV0YT4KTMInWQAAAVBJREFUKBVtkz0vREEUhsdXgo5qJXohkUgQ0fgFNFpR2V5ClP6CQu9PiB6lEL1I7B9A4/treZ47c252s97k2ffMmZkz5869m1JKL/AFbzAHaiRbmsIf4BdaMAZqMFsOXNxXkroKbxCPV5l8yHOJLVipn9/vEreLa7FguSN3S2ynA/ATeQuI8tTY6OOY34DQaQnq9mPCDtxoBwuRxPfAvPMWnARlB12KAi6eLTPruOOP4gcl33O6+Sjgc83DJkRH+h2MgorLzaPy68W48BG2S+xYnmAa1L+nOxEduMH3fgjGFvZeVkANZau68B6CrgJxWosFFpF7iG+h5wKZqwt42qIJtARu/ix+gqsosEq8D35o6R3c7OL4lAnTDljEe9B3Qa2BYzmHemDCt6Diwo6JY7E+A82OnN9HuoBruAQvUQ1nSxP4GVzBDRyBfygf6RW2/gD3NmEv+K/DZgAAAABJRU5ErkJggg==
[klarna-url]: https://github.com/klarna-incubator
