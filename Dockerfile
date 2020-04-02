FROM actionzh/jdk:8
ADD build/libs/cs-tools-1.0.jar /app/cs-tools.jar

CMD java -Dfile.encoding=UTF-8 -Xms512m -Xmn256m -cp /app/cs-tools.jar:/tmp org.springframework.boot.loader.JarLauncher
