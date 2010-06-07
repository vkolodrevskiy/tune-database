package com.ospu.constant;

import com.ospu.util.DatabaseConnection;

/**
 * Generates random constant.
 * Constant value can be taken both from database or randomly generate. 
 *
 * @author vkolodrevskiy
 */
public class ConstantGenerator {

    private DatabaseConstantGenerator databaseConstantGenerator;
    private RandomConstantGenerator randomConstantGenerator;

    public ConstantGenerator() {
        this.databaseConstantGenerator =
                new DatabaseConstantGenerator(DatabaseConnection.getConnectionToTestingDatabase());
        this.randomConstantGenerator =
                new RandomConstantGenerator(DatabaseConnection.getConnectionToTestingDatabase());
    }

    // ------------------------------------------------------------------------
    /**
     * Generates constant for the given table column.
     *
     * @param tableAndColumn table column for which to generate constant value.
     * @param useDatabase defines whether to take constants from the database.
     * @return result constant.
     */
    public Object generate(TableAndColumn tableAndColumn, boolean useDatabase) {
        final double random = Math.random();

        final Object databaseConstant;
        final Object randomConstant;

        if(useDatabase) {
            if(random < 0.5) { // take constant from database
                databaseConstant = databaseConstantGenerator.generate(tableAndColumn);
                if(databaseConstant == null)
                    return randomConstantGenerator.generate(tableAndColumn);
                else return databaseConstant;
            }
            else { // generate randomly. if could not generate try to take constant from database
                randomConstant = randomConstantGenerator.generate(tableAndColumn);
                if(randomConstant == null)
                    return databaseConstantGenerator.generate(tableAndColumn);
                else return randomConstant;
            }
        }
        else return randomConstantGenerator.generate(tableAndColumn);
    }
}
