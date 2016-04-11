# json_test

The code is built using Java8 & needs java8 on your classpath.
All other dependancies are contained within the created executable jar

1) tests only
     mvn clean compile test
     
2) compile & create executable jar (with dependancies)
    mvn clean compile test assembly:single
    
3) to execute the program 
        with a default URL of 'http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html'
   java -cp "target\sainsbury-1.0-SNAPSHOT-jar-with-dependencies.jar" uk.co.beanfactory.sainsbury.SainsburyDemo
   
   
4) to execute the program 
        but override the URL with 'http://foo.html'
    java -cp "target\sainsbury-1.0-SNAPSHOT-jar-with-dependencies.jar" uk.co.beanfactory.sainsbury.SainsburyDemo http://foo.html
