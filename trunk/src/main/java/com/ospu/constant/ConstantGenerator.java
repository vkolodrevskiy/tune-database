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
        this.databaseConstantGenerator = new DatabaseConstantGenerator(DatabaseConnection.getConnectionToTestingDatabase());
        this.randomConstantGenerator = new RandomConstantGenerator(DatabaseConnection.getConnectionToTestingDatabase());
    }

    public Object generate(TableAndColumn tableAndColumn) {
        double random = Math.random();

        Object databaseConstant;
        Object randomConstant;

        if(random < 0.5) {
            databaseConstant = databaseConstantGenerator.generate(tableAndColumn);
            if(databaseConstant == null)
                return randomConstantGenerator.generate(tableAndColumn);
            else return databaseConstant;
        }
        else {
            randomConstant = randomConstantGenerator.generate(tableAndColumn);
            if(randomConstant == null)
                return databaseConstantGenerator.generate(tableAndColumn);
            else return randomConstant;
        }
    }
}
