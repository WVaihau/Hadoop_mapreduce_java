# Yarn & MapReduce 2 - WILLIAMU Vaihau - GHEYOUCHE Eya

Note :
- To get our full project folder : [click here](https://github.com/WVaihau/Hadoop_mapreduce_java)

- All our code are disponible inside the project folder

- username : your user name on the cluster

  You can have it with

  ```bash
  whoami
  ```

  ```
  [v.williamu@hadoop-edge01 ~]$ whoami
  v.williamu
  ```

## Setup

### Download trees.csv

1. Download [trees.csv](https://github.com/makayel/hadoop-examples-mapreduce/blob/main/src/test/resources/data/trees.csv)

2. Put it on the cluster

### Anthenticated to Kerberos

```bash
kinit -V
```

```bash
[v.williamu@hadoop-edge01 ~]$ kinit -V
Using principal: v.williamu@EFREI.ONLINE
Password for v.williamu@EFREI.ONLINE:
Authenticated to Kerberos v5
```

### Put trees.csv in hdfs

```bash
hdfs dfs -put trees.csv
```

```bash
[v.williamu@hadoop-edge01 ~]$ hdfs dfs -put trees.csv
[v.williamu@hadoop-edge01 ~]$ hdfs dfs -ls trees.csv
-rw-r--r--   3 v.williamu v.williamu     172050 2021-10-02 23:22 trees.csv
```

### Download our application

1. Download [our application](https://github.com/WVaihau/Hadoop_mapreduce_java/blob/main/target/mapreduce_vaihau-1-jar-with-dependencies.jar)

2. Put it in the cluster

### Declare an alias to simplify our future command

```bash
alias mapreduce='yarn jar mapreduce_vaihau-1-jar-with-dependencies.jar'
```

### Display the different disponible job

```bash
mapreduce
```

```bash
[v.williamu@hadoop-edge01 ~]$ mapreduce
An example program must be given as the first argument.
Valid program names are:
  counter: A MapReduce job that counts the words in the input files.
  dts: A MapReduce job that displays the list of distinct containing trees and number in trees.csv
  kinds: A MapReduce job that calculates the number of trees of each kinds in trees.csv
  mostTreesDistrict:  A MapReduce job that get the district containing the most trees in trees.csv
  oldestTreeDistrict: A MapReduce job that get district(s) which have the oldest trees in trees.csv
  sortbyheight: A MapReduce job that sort the trees from the smallest to the largest in trees.csv
  species: A MapReduce job that displays the list of different species trees in trees.csv
  tallest: A MapReduce job that get the height of the tallest tree of each kind in trees.csv
```

## 1.8 - Remarkable trees of Paris

### 1.8.1 - District containing trees

For this one, we decided to also show the number of trees in each district. As we can see in the result below the 16th district has the most trees. This information
will be useful to verify one of our program below.

```bash
mapreduce dts /user/<username>/trees.csv /user/<username>/dts_out
```

```bash
[v.williamu@hadoop-edge01 ~]$ mapreduce dts /user/v.williamu/trees.csv /user/v.williamu/dts_out
...
21/10/03 00:01:14 INFO mapreduce.Job:  map 0% reduce 0%
12587 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 0% reduce 0%
21/10/03 00:01:23 INFO mapreduce.Job:  map 100% reduce 0%
21699 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 100% reduce 0%
21/10/03 00:01:32 INFO mapreduce.Job:  map 100% reduce 100%
30796 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 100% reduce 100%
21/10/03 00:01:32 INFO mapreduce.Job: Job job_1630864376208_1407 completed successfully
30815 [main] INFO org.apache.hadoop.mapreduce.Job  - Job job_1630864376208_1407 completed successfully
...
        File Input Format Counters
                Bytes Read=16778
        File Output Format Counters
                Bytes Written=80
```

Show the result

```bash
hdfs dfs -cat dts_out/part-r-00000
```

```bash
[v.williamu@hadoop-edge01 ~]$ hdfs dfs -cat dts_out/part-r-00000
11      1
12      29
13      2
14      3
15      1
16      36
17      1
18      1
19      6
20      3
3       1
4       1
5       2
6       1
7       3
8       5
9       1
```

### 1.8.2 - Show all existing species
```bash
mapreduce species /user/<username>/trees.csv /user/<username>/species_out
```

```bash
[v.williamu@hadoop-edge01 ~]$ mapreduce species /user/v.williamu/trees.csv /user/v.williamu/species_out
...
21/10/03 00:11:52 INFO mapreduce.Job:  map 0% reduce 0%
11545 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 0% reduce 0%
21/10/03 00:12:00 INFO mapreduce.Job:  map 100% reduce 0%
19658 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 100% reduce 0%
21/10/03 00:12:10 INFO mapreduce.Job:  map 100% reduce 100%
28761 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 100% reduce 100%
21/10/03 00:12:10 INFO mapreduce.Job: Job job_1630864376208_1415 completed successfully
28778 [main] INFO org.apache.hadoop.mapreduce.Job  - Job job_1630864376208_1415 completed successfully
...
        File Input Format Counters
                Bytes Read=16778
        File Output Format Counters
                Bytes Written=542
```

Show the result

```bash
hdfs dfs -cat species_out/part-r-00000
```

```bash
[v.williamu@hadoop-edge01 ~]$ hdfs dfs -cat species_out/part-r-00000
araucana        1
atlantica       2
australis       1
baccata 2
bignonioides    1
biloba  5
bungeana        1
cappadocicum    1
carpinifolia    4
colurna 3
coulteri        1
decurrens       1
dioicus 1
distichum       3
excelsior       1
fraxinifolia    2
giganteum       5
giraldii        1
glutinosa       1
grandiflora     1
hippocastanum   3
ilex    1
involucrata     1
japonicum       1
kaki    2
libanii 2
monspessulanum  1
nigra   3
nigra laricio   1
opalus  1
orientalis      8
papyrifera      1
petraea 2
pomifera        1
pseudoacacia    1
sempervirens    1
serrata 1
stenoptera      1
suber   1
sylvatica       8
tomentosa       2
tulipifera      2
ulmoides        1
virginiana      2
x acerifolia    11
```

### 1.8.3 - Number of trees by kinds

```bash
mapreduce kinds /user/<username>/trees.csv /user/<username>/kinds_out
```

```bash
[v.williamu@hadoop-edge01 ~]$ mapreduce kinds /user/v.williamu/trees.csv /user/v.williamu/kinds_out
...
21/10/03 00:18:39 INFO mapreduce.Job:  map 0% reduce 0%
12607 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 0% reduce 0%
21/10/03 00:18:47 INFO mapreduce.Job:  map 100% reduce 0%
20723 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 100% reduce 0%
21/10/03 00:18:52 INFO mapreduce.Job:  map 100% reduce 100%
25777 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 100% reduce 100%
21/10/03 00:18:53 INFO mapreduce.Job: Job job_1630864376208_1418 completed successfully
26804 [main] INFO org.apache.hadoop.mapreduce.Job  - Job job_1630864376208_1418 completed successfully
...
        File Input Format Counters
                Bytes Read=16778
        File Output Format Counters
                Bytes Written=390
```

Show the result

```bash
hdfs dfs -cat kinds_out/part-r-00000
```

```bash
[v.williamu@hadoop-edge01 ~]$ hdfs dfs -cat kinds_out/part-r-00000
Acer    3
Aesculus        3
Ailanthus       1
Alnus   1
Araucaria       1
Broussonetia    1
Calocedrus      1
Catalpa 1
Cedrus  4
Celtis  1
Corylus 3
Davidia 1
Diospyros       4
Eucommia        1
Fagus   8
Fraxinus        1
Ginkgo  5
Gymnocladus     1
Juglans 1
Liriodendron    2
Maclura 1
Magnolia        1
Paulownia       1
Pinus   5
Platanus        19
Pterocarya      3
Quercus 4
Robinia 1
Sequoia 1
Sequoiadendron  5
Styphnolobium   1
Taxodium        3
Taxus   2
Tilia   1
Ulmus   1
Zelkova 4
```

### 1.8.4 - Maximum height per kind of tree

Display the tallest by kind

```bash
mapreduce tallest /user/<username>/trees.csv /user/<username>/tallest_out
```

```bash
[v.williamu@hadoop-edge01 ~]$ mapreduce tallest /user/v.williamu/trees.csv /user/v.williamu/tallest_out
...
21/10/03 00:23:00 INFO mapreduce.Job:  map 0% reduce 0%
12468 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 0% reduce 0%
21/10/03 00:23:08 INFO mapreduce.Job:  map 100% reduce 0%
20567 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 100% reduce 0%
21/10/03 00:23:17 INFO mapreduce.Job:  map 100% reduce 100%
29656 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 100% reduce 100%
21/10/03 00:23:17 INFO mapreduce.Job: Job job_1630864376208_1420 completed successfully
29675 [main] INFO org.apache.hadoop.mapreduce.Job  - Job job_1630864376208_1420 completed successfully
...
        File Input Format Counters
                Bytes Read=16778
        File Output Format Counters
                Bytes Written=496
```

Show the result

```bash
hdfs dfs -cat tallest_out/part-r-00000
```

```bash
[v.williamu@hadoop-edge01 ~]$ hdfs dfs -cat tallest_out/part-r-00000
Acer    16.0
Aesculus        30.0
Ailanthus       35.0
Alnus   16.0
Araucaria       9.0
Broussonetia    12.0
Calocedrus      20.0
Catalpa 15.0
Cedrus  30.0
Celtis  16.0
Corylus 20.0
Davidia 12.0
Diospyros       14.0
Eucommia        12.0
Fagus   30.0
Fraxinus        30.0
Ginkgo  33.0
Gymnocladus     10.0
Juglans 28.0
Liriodendron    35.0
Maclura 13.0
Magnolia        12.0
Paulownia       20.0
Pinus   30.0
Platanus        45.0
Pterocarya      30.0
Quercus 31.0
Robinia 11.0
Sequoia 30.0
Sequoiadendron  35.0
Styphnolobium   10.0
Taxodium        35.0
Taxus   13.0
Tilia   20.0
Ulmus   15.0
Zelkova 30.0
```

### 1.8.5 - Sort the trees height from smallest to largest

```bash
mapreduce sortbyheight /user/<username>/trees.csv /user/<username>/sortbyheight_out
```

```bash
[v.williamu@hadoop-edge01 ~]$ mapreduce sortbyheight /user/v.williamu/trees.csv /user/v.williamu/sortbyheight_out
...
21/10/03 00:27:21 INFO mapreduce.Job:  map 0% reduce 0%
11477 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 0% reduce 0%
21/10/03 00:27:29 INFO mapreduce.Job:  map 100% reduce 0%
19589 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 100% reduce 0%
21/10/03 00:27:38 INFO mapreduce.Job:  map 100% reduce 100%
28691 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 100% reduce 100%
21/10/03 00:27:38 INFO mapreduce.Job: Job job_1630864376208_1423 completed successfully
28707 [main] INFO org.apache.hadoop.mapreduce.Job  - Job job_1630864376208_1423 completed successfully
...
        File Input Format Counters
                Bytes Read=16778
        File Output Format Counters
                Bytes Written=2055
```

Show the result

```bash
hdfs dfs -cat sortbyheight_out/part-r-00000
```

```bash
3 - Fagus :     2.0
89 - Taxus :    5.0
62 - Cedrus :   6.0
39 - Araucaria :        9.0
44 - Styphnolobium :    10.0
32 - Quercus :  10.0
95 - Pinus :    10.0
61 - Gymnocladus :      10.0
63 - Fagus :    10.0
4 - Robinia :   11.0
93 - Diospyros :        12.0
66 - Magnolia :         12.0
50 - Zelkova :  12.0
7 - Eucommia :  12.0
48 - Acer :     12.0
58 - Diospyros :        12.0
33 - Broussonetia :     12.0
71 - Davidia :  12.0
36 - Taxus :    13.0
6 - Maclura :   13.0
68 - Diospyros :        14.0
96 - Pinus :    14.0
94 - Diospyros :        14.0
91 - Acer :     15.0
5 - Catalpa :   15.0
70 - Fagus :    15.0
2 - Ulmus :     15.0
98 - Quercus :  15.0
28 - Alnus :    16.0
78 - Acer :     16.0
75 - Zelkova :  16.0
16 - Celtis :   16.0
64 - Ginkgo :   18.0
83 - Zelkova :  18.0
23 - Aesculus :         18.0
60 - Fagus :    18.0
34 - Corylus :  20.0
51 - Platanus :         20.0
43 - Tilia :    20.0
15 - Corylus :  20.0
11 - Calocedrus :       20.0
1 - Corylus :   20.0
8 - Platanus :  20.0
20 - Fagus :    20.0
35 - Paulownia :        20.0
12 - Sequoiadendron :   20.0
87 - Taxodium :         20.0
13 - Platanus :         20.0
10 - Ginkgo :   22.0
47 - Aesculus :         22.0
86 - Platanus :         22.0
14 - Pterocarya :       22.0
88 - Liriodendron :     22.0
18 - Fagus :    23.0
24 - Cedrus :   25.0
31 - Ginkgo :   25.0
92 - Platanus :         25.0
49 - Platanus :         25.0
97 - Pinus :    25.0
84 - Ginkgo :   25.0
73 - Platanus :         26.0
65 - Pterocarya :       27.0
42 - Platanus :         27.0
85 - Juglans :  28.0
76 - Pinus :    30.0
19 - Quercus :  30.0
72 - Sequoiadendron :   30.0
54 - Pterocarya :       30.0
29 - Zelkova :  30.0
27 - Sequoia :  30.0
25 - Fagus :    30.0
41 - Platanus :         30.0
77 - Taxodium :         30.0
55 - Platanus :         30.0
69 - Pinus :    30.0
38 - Fagus :    30.0
59 - Sequoiadendron :   30.0
52 - Fraxinus :         30.0
37 - Cedrus :   30.0
22 - Cedrus :   30.0
30 - Aesculus :         30.0
80 - Quercus :  31.0
9 - Platanus :  31.0
82 - Platanus :         32.0
46 - Ginkgo :   33.0
45 - Platanus :         34.0
56 - Taxodium :         35.0
81 - Liriodendron :     35.0
17 - Platanus :         35.0
53 - Ailanthus :        35.0
57 - Sequoiadendron :   35.0
26 - Platanus :         40.0
74 - Platanus :         40.0
40 - Platanus :         40.0
90 - Platanus :         42.0
21 - Platanus :         45.0
```

### 1.8.6 - District containing the oldest tree

```bash
mapreduce oldestTreeDistrict /user/<username>/trees.csv /user/<username>/oldestTreeDistrict_out
```

```bash
[v.williamu@hadoop-edge01 ~]$ mapreduce oldestTreeDistrict /user/v.williamu/trees.csv /user/v.williamu/oldestTreeDistrict_out
...
21/10/03 00:31:30 INFO mapreduce.Job:  map 0% reduce 0%
12588 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 0% reduce 0%
21/10/03 00:31:38 INFO mapreduce.Job:  map 100% reduce 0%
20693 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 100% reduce 0%
21/10/03 00:31:47 INFO mapreduce.Job:  map 100% reduce 100%
29784 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 100% reduce 100%
21/10/03 00:31:48 INFO mapreduce.Job: Job job_1630864376208_1424 completed successfully
30804 [main] INFO org.apache.hadoop.mapreduce.Job  - Job job_1630864376208_1424 completed successfully
...
        File Input Format Counters
                Bytes Read=16778
        File Output Format Counters
                Bytes Written=7
```

Show the result

```bash
hdfs dfs -cat oldestTreeDistrict_out/part-r-00000
```

```bash
[v.williamu@hadoop-edge01 ~]$ hdfs dfs -cat oldestTreeDistrict_out/part-r-00000
1601    5
```

### 1.8.7 - District containing the most tree

```bash
mapreduce mostTreesDistrict /user/<username>/trees.csv /user/<username>/mostTreesDistrict_out
```

```bash
[v.williamu@hadoop-edge01 ~]$ mapreduce mostTreesDistrict /user/v.williamu/trees.csv /user/v.williamu/mostTreesDistrict_out
...
21/10/03 00:35:40 INFO mapreduce.Job:  map 0% reduce 0%
11650 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 0% reduce 0%
21/10/03 00:35:49 INFO mapreduce.Job:  map 100% reduce 0%
20778 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 100% reduce 0%
21/10/03 00:35:54 INFO mapreduce.Job:  map 100% reduce 100%
25831 [main] INFO org.apache.hadoop.mapreduce.Job  -  map 100% reduce 100%
21/10/03 00:35:54 INFO mapreduce.Job: Job job_1630864376208_1427 completed successfully
25851 [main] INFO org.apache.hadoop.mapreduce.Job  - Job job_1630864376208_1427 completed successfully
...
        File Input Format Counters
                Bytes Read=16778
        File Output Format Counters
                Bytes Written=6
```

Show the result

```bash
hdfs dfs -cat mostTreesDistrict_out/part-r-00000
```

```bash
[v.williamu@hadoop-edge01 ~]$ hdfs dfs -cat mostTreesDistrict_out/part-r-00000
16      36
```

Insight : We can see that this is the desired result as we noticed in exercise 1.8.1 .

[LAB FINISHED]
