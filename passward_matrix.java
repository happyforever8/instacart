
全部都是原题，没有在地里看到特别详细的coding面经，所以还是决定分享一下：
第一题：
1. coding，给定一个文本path，文本样式：
[2,4]
S3KDA4
4ASDSD
ACEEDS
ASDEED
RTRYYU

读取文本并返回coordinator指定位置对的字符。[0,0]对应的是左下角的字符。
2. followup：同样文本路径，但是文件包含多个block，每个block第一行是一个数字，对应的是这个字符在password里面的index，要求返回这个password。
1
[2,4]
S3KDA4
4ASDSD
ACEEDS
ASDEED
RTRYYU

0
[0,0]
I3KDA4
XTRYYU

给定的例子应该返回XK
3. followup：和2一样文本路径和文本，但是有多个password，要求返回第一个password。具体怎么判断当一个password结束，是根据当前字符所在的index有没有重复。比如：

1
[2,4]
S3KDA4
4ASDSD
ACEEDS
ASDEED
RTRYYU

0
[0,0]
I3KDA4
XTRYYU

1          #《---index 1已经出现过，所以从这儿开始是第二个password，可以返回第一个password为：XK
[0,0]
L3BDA4

第二题：expression calculation
1. 给定一个computeExp和expressions。
computeExp
T3
expressions
T1=1
T2=2
T3=T4
T4=T5
T5=T2

应该返回2

2. followup，expression可能包含+，-，但最多只有一个+或者-。（不存在比如T1=T2+T3+1）
3. foll‍‍‍‍‍‍‍‍‌‍‌‌‍‌‌‌‌‌owup，expression可能有循环，比如
T1=1

T2=2
T3=T4+T5
T4=T5
T5=T4
这个情况 返回“IMPOSSIBLE”。

设计题：design shopper payment verification service。问了api，security，data model， scale。

