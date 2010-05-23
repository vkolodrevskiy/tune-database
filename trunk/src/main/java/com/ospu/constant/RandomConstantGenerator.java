package com.ospu.constant;

import com.ospu.metadata.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Random;

/**
 * Generates random constants.
 *
 * @author vkolodrevskiy
 */
public class RandomConstantGenerator {

    private DatabaseStructure databaseStructure;

    public RandomConstantGenerator(Connection connection) {
        this.databaseStructure = PostgresMetadata.getDatabaseStructure(connection);
    }

    // ------------------------------------------------------------------------
    // TODO: add generation for all data types
    public Object generate(TableAndColumn tableAndColumn) {

        String type = getColumnType(tableAndColumn);

        if(type == null) return null;

        // numbers
        if(type.equals("int2")) {
            Random r = new Random();
            return r.nextInt(30000) + 1;
        }
        if(type.equals("int4")) {
            Random r = new Random();
            return r.nextInt(30000) + 1;
        }
        if(type.equals("int8")) {
            Random r = new Random();
            return r.nextInt(30000) + 1;
        }
        if(type.equals("year")) {
            Random r = new Random();
            return r.nextInt(2011) + 1;
        }
        if(type.equals("numeric")) {
            Random r = new Random();
            return r.nextDouble() + r.nextInt(100);
        }
        if(type.equals("serial")) {
            Random r = new Random();
            return r.nextInt(30000) + 1;
        }
        if(type.equals("bigserial")) {
            Random r = new Random();
            return r.nextInt(30000) + 1;
        }

        // text
        if(type.equals("varchar")) {
            return Long.toHexString(Double.doubleToLongBits(Math.random()));
        }
        if(type.equals("text")) {
            return Long.toHexString(Double.doubleToLongBits(Math.random()));
        }
        if(type.equals("bpchar")) {
            return Long.toHexString(Double.doubleToLongBits(Math.random()));
        }
        
        // time
        if(type.equals("timestamp")) {
            return new Timestamp(System.currentTimeMillis());
        }
        if(type.equals("date")) {
            return new Date(System.currentTimeMillis());
        }
        // boolean
        if(type.equals("bool")) {
            Random r = new Random();
            return r.nextInt(2);
        }        

        return null;
    }

    private String getColumnType(TableAndColumn tableAndColumn) {
        if(databaseStructure == null)
            return null;           

        for(DatabaseSchema schema: databaseStructure.getSchemas()) {
            for(DatabaseTable table: schema.getTables()) {
                if(table.getName().equals(tableAndColumn.getTable())) {
                    for(DatabaseColumn column: table.getColumns()) {
                        if(column.getName().equals(tableAndColumn.getColumn())) {
                            return column.getType();
                        }
                    }
                }
            }
        }
        return null;
    }
}
