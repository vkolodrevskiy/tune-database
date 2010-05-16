package com.ospu.constant;

/**
 * Represents all possible postgres data types.
 *
 * @author vkolodrevskiy
 */
public enum DataType {
    // FIXME: need to add all possible postgres column data types.
    //        see http://developer.postgresql.org/pgdocs/postgres/datatype.html

    // signed two-byte integer
    int2,

    // signed four-byte integer
    int4,

    // signed eight-byte integer
    int8,

    // autoincrementing eight-byte integer
    bigserial,

    // variable-length character string
    varchar,

    // variable-length character string
    text,

    // date and time
    timestamp,

    // this data type is not supported by the generator
    unknown
}
