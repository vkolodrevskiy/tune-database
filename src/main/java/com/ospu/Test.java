package com.ospu;

import com.ospu.constant.ConstantGenerator;
import com.ospu.constant.RandomConstantGenerator;
import com.ospu.constant.TableAndColumn;
import com.ospu.util.DatabaseConnection;
import com.ospu.validator.QueryValidator;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * FIXME: add description.
 *
 * @author vkolodrevskiy
 *         To change this template use File | Settings | File Templates.
 */
public class Test {


    public static void main(String args[]) {


        //RandomConstantGenerator generator = new RandomConstantGenerator(DatabaseConnection.getConnectionToTestingDatabase());

        //generator.generate(new TableAndColumn("statistica", "start_time"));
        //ConstantGenerator gen = new ConstantGenerator();
        //gen.generate(new TableAndColumn("templates", "id_tem"));

        /*List<String> testQueries = new ArrayList<String>();
        testQueries.add("select address, phone from address;");
        testQueries.add("select district from address;");
        testQueries.add("delete from film where length = '48';");
        testQueries.add("INSERT INTO film (title, length, description) VALUES ('85', '3fdcb49cde665762', '3fc29c5b4fa09024');");
        testQueries.add("UPDATE actor SET actor_id='1', last_name='3fe0a2e2ad3a0929' WHERE last_name='85';");
        testQueries.add("update customer set store_id = (select store_id from customer where first_name = '3fd8ff6ceaea99a6') last_name = (select last_name from customer where first_name = '3fd8ff6ceaea99a6') where first_name = 'KELLY';");

        List<String> result = QueryValidator.validate(testQueries);

        for(String s:result)
            System.out.println(s);
*/

        Random r = new Random();

        for(int i=0; i<10;i++)
            System.out.println(r.nextDouble() + r.nextInt(100));

        //DatabaseConstantGenerator gen = new DatabaseConstantGenerator();

        //gen.generate(new TableAndColumn("client", "id"));

//        DatabaseStructure structure = PostgresMetadata.getDatabaseStructure(DatabaseConnection.getConnectionToTestingDatabase());

        //QueryGenerator generator = new QueryGenerator();
//        Random r = new Random();
//
//        for(int i = 0; i < 100; i++) {
//            System.out.println(r.nextInt(10));
//        }


//        String s = " select into  (table1.column1, column2) values (constant1, constant2) table1 column3 table1";


//        Runner r = new Runner(5, "test1");
//        r.runTests();

//
//        List<String> templates = new ArrayList<String>();
//        templates.add("select * from client");
//        templates.add("select * from client1");
//        templates.add("select * from client_transactions");
//
//        RunTestThread t1 = new RunTestThread(templates);
//
//        // create ExecutorService to manage threads
//        ExecutorService threadExecutor = Executors.newFixedThreadPool(5);
//        threadExecutor.submit(t1);
//        // shutdown worker threads
//        threadExecutor.shutdown();
//
//        // wait for the pool to shutdown
//        while ( !threadExecutor.isTerminated() ) {
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) { System.out.println(e.getMessage()); }
//        }
//
//        System.out.println("bla; duration = " + t1.getDuration());




//        TemplateParser parser = new TemplateParser();
//        Set<Column> columns = parser.getColumns(s);
//
//        for(Column col: columns) {
//            System.out.println("name: " + col.getName() + " table: " + col.getTable());
//        }


//        String s = " FOO[ column66 column66 BAmaR] table1111. column11222; fsdus  table1.column888";
//
//        Pattern MY_PATTERN = Pattern.compile("(\\bcolumn)\\w+\\b");
//        Matcher m = MY_PATTERN.matcher(s);
//
//        Set<String> tables = new HashSet<String>();
//        while (m.find()) {
//            tables.add(m.group(0));
//        }
//        System.out.println(tables.size());
//
//        m.reset();

        /*while (m.find()) {
            i++;
        }*/
//        System.out.println(i);
        /*while (m.find()) {
            System.out.println("[" + m.group(0) + "]");
            System.out.println("index = " + s.indexOf(m.group(0)));

            if(s.charAt(s.indexOf(m.group(0)) - 1) == '.') {
                System.out.println(m.group(0) + " has table");

                String s2 = s.substring(0, s.indexOf(m.group(0)) - 1);
                s2 = s2.trim();
                System.out.println(s2.substring(s2.lastIndexOf(" ") + 1));
            }
        }*/
    }
}
