cd D:\Courses\Algorithms-Design\Home\out\production\Home
jar cfv sneg.jar *
mv sneg.jar ..\..\..
cd ..\..\..
java -Xms1400m -Xmx1400m -Xss50m -cp sneg.jar com.sneg.week4.Scc D:\Courses\Algorithms-Design\Home\Week-4\SCC.txt