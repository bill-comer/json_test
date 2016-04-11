# json_test

The code is built using Java8 & needs java8 on your classpath.
All other dependancies are contained within the created executable jar

- tests only
     * mvn clean compile test
     
- compile & create executable jar (with dependancies)
    * mvn clean compile test assembly:single
    * this will create a jar in the directory 'target' with a defined main class
    
- to execute the program
        with a default URL of 'http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html'
   * java -jar "target\sainsbury-1.0-SNAPSHOT-jar-with-dependencies.jar"
   
   
- to execute the program
        but override the URL with 'http://foo.html'
    * java -cp "target\sainsbury-1.0-SNAPSHOT-jar-with-dependencies.jar" http://foo.html
