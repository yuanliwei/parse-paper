var fs, paperData, require, start;

require = function(name) {
  if(name == "fs")
    return fs;
  if(name == "./split")
    return split;
  if(name == "./tohtml")
    return tohtml;
};
split = exports = {}
tohtml = exports

fs = function() {};

fs.readFileSync = function() {
  return paperData;
};

fs.writeFileSync = function(path, data) {
  var output;
  output = document.getElementById('output');
  return output.value = data;
};

fs.appendFileSync = function(path, data) {
  var output;
  output = document.getElementById('output');
  return output.value += data;
};

start = function() {};

paperData = "数学试题分析报告\n1、 选择题\n1 ．设扇形圆心角为 公式:rId7 ，面积为 公式:rId9 ，将它围成一个圆锥，则此圆锥的表面积是（ ）\nA． 公式:rId11 B． 公式:rId13 C． 公式:rId15 D． 公式:rId17\n【答案】 B\n【解析】 设扇形的半径为 R ，由题意得\n公式:rId19 ，扇形弧长为 公式:rId21 ，故圆锥的底面半径为1，圆锥的表面积是 公式:rId23 ．\n【点评】 在天学网校自主招生强手营第一阶段的讲义中，我们讲解了弧度制和角度制的关系与由来，此题求出扇形弧长并由此给出圆锥底面圆的半径即可。\n【难度】 较低\n2． 将10个人分成3组，每组人数分别为3，3，4，则不同的方法（ ）种．\nA． 公式:rId25 B． 公式:rId27 C． 公式:rId29 D． 公式:rId31\n【答案】 C\n【解析】 首先任取3人有 公式:rId33 种取法，剩下的在7人里面取3人有 公式:rId35 种取法，最后剩下4人，但由于先取的3人和之后取的3人（人数相同）不存在先取和后取的排列，因此总的分法数为 公式:rId37 ．\n【点评】 在天学网校强手营第一阶段讲义中，我们讲授映射法解排列组合题目时给大家特别补充提到的一个“分堆分组问题”，这道题目是典型的“分堆”。\n【难度】 中等\n";
paperData = "数学试题分析报告\n1、 选择题\n1 ．设扇形圆心角为 公式:rId7 ，面积为 公式:rId9 ，将它围成一个圆锥，则此圆锥的表面积是（ ）\nA． 公式:rId11 B． 公式:rId13 C． 公式:rId15 D． 公式:rId17\n【答案】 B\n【解析】 设扇形的半径为 R ，由题意得\n公式:rId19 ，扇形弧长为 公式:rId21 ，故圆锥的底面半径为1，圆锥的表面积是 公式:rId23 ．\n【点评】 在天学网校自主招生强手营第一阶段的讲义中，我们讲解了弧度制和角度制的关系与由来，此题求出扇形弧长并由此给出圆锥底面圆的半径即可。\n【难度】 较低\n2． 将10个人分成3组，每组人数分别为3，3，4，则不同的方法（ ）种．\nA． 公式:rId25 B． 公式:rId27 C． 公式:rId29 D． 公式:rId31\n【答案】 C\n【解析】 首先任取3人有 公式:rId33 种取法，剩下的在7人里面取3人有 公式:rId35 种取法，最后剩下4人，但由于先取的3人和之后取的3人（人数相同）不存在先取和后取的排列，因此总的分法数为 公式:rId37 ．\n【点评】 在天学网校强手营第一阶段讲义中，我们讲授映射法解排列组合题目时给大家特别补充提到的一个“分堆分组问题”，这道题目是典型的“分堆”。\n【难度】 中等\n3． 函数 公式:rId39 满足：对于任意的实数 公式:rId41 有 公式:rId43 ，已知 公式:rId45 ， 公式:rId47 ， 则 公式:rId49 的值是（ ）\nA． 公式:rId51 B． 公式:rId53 C． 公式:rId55 D． 公式:rId57\n【答案】 B\n解：由 公式:rId45 和 公式:rId47 得，\n公式:rId61 ，\n公式:rId63 ．\n猜想 公式:rId65 ，由第二数学归纳法证明如下，\n假设 公式:rId67 对 公式:rId69 都成立，则\n由 公式:rId71 可变为 公式:rId73 得，\n公式:rId75 ，\n公式:rId77 ，\n所以猜想成立．则 公式:rId79 ．\n【点评】 这是一道函数方程的问题，在天学网校强手营第二阶段的课程中，我们对于函数方程做过详细的讲解，这道题目就是我们讲的“特殊函数法”，由于是选择题，答案是存在且唯一的，只需要将函数 公式:rId39 看成是特殊的一次函数，问题就迎刃而解了。\n【难度】 中上。\n4． 已知函数 公式:rId82 的值域是 公式:rId84 ，则实数 公式:rId86 的取值范围是（ ）\nA． 公式:rId88 B． 公式:rId90 C． 公式:rId92 D． 公式:rId94\n【答案】 D\n解：由题意得 公式:rId96 ，则说明 公式:rId98 与 x 轴有交点，故 公式:rId100 ，解得 公式:rId102 ．\n【点评】 在天学网校强手营第一阶段的函数一节和第二阶段的函数一节均有此题的原型，含参数的二次函数的复合。\n【难度】 中等\n5． 设 公式:rId104 均为负数，且满足 公式:rId106 ，则 公式:rId108 具有（ ）\nA．最大值 公式:rId110 B．最小值 公式:rId110 C．最大值 公式:rId113 D．最小值 公式:rId115\n【答案】 D\n解： 公式:rId117 ，则 公式:rId119 ，即 公式:rId121 ．\n设 公式:rId123 ，易知 公式:rId125 在 公式:rId127 上为减函数，所以 公式:rId108 不存在最大值，存在最小值，最小值为 公式:rId130 ．\n【点评】 均值不等式，在天学网校强手营第一阶段和第二阶段的集合论章节与不等式章节均有讲解。\n【难度】中上\n6 ．使得函数 公式:rId132 成为区间 公式:rId134 上的奇函数的常数 公式:rId136 的值是（ ）\nA． 公式:rId138 B． 公式:rId140 C． 公式:rId142 D．不存在\n【答案】 B\n解：由题意得， 公式:rId144  ，且 公式:rId146  ．\n由  得 公式:rId148 ；\n由  得 公式:rId150 ，\n设 公式:rId152 ，\n则 公式:rId154 ，\n公式:rId156 ，\n所以 公式:rId158 ，\n则 公式:rId160 ；\n下面证明： 公式:rId162 ，两边同时取正切值得\n左边 公式:rId164 右边，\n所以 公式:rId166 ．\n【点评】 函数奇偶性问题，在天学网校强手营第一阶段的讲义中有所涉及，此题只需按照第一阶段讲义中的例题令 公式:rId168 即可。\n【难度】较低\n二、 解答题\n7. 证明 公式:rId170 是无理数．\n【解 析 】 有 理 数 关 于 加 减 乘除 四 则 运 算 封 闭．\n反证法：若 公式:rId172 是有理数， 公式:rId174 ，则 公式:rId176 也是有理数，\n则 公式:rId178 也是有理数，…，则 公式:rId180 也是有理数，而 公式:rId182 是无理数，矛盾，故 公式:rId172 是无理数，证毕 ．\n【点评】 三倍角公式的应用，是天学网校强手营第一阶段和第二阶段课程的共同重点，另外此题涉及到的有理数的封闭性以及反证法也都讲解过。\n【难度】难。\n8 ． 已知实系数二次函数 公式:rId185 与 公式:rId187 满足方程 公式:rId189 和 公式:rId191 只有一对重根，已知 公式:rId193 有两个不同的实根，求证 公式:rId195 没有实根．\n【解 析 】若对于 公式:rId197 ， 公式:rId199 ， 公式:rId201 ，\n则有 公式:rId203 ，则 公式:rId205 最多有二等实根，\n同理若对于 公式:rId197 ， 公式:rId208 ， 公式:rId210 ，也会矛盾，故二次函数 公式:rId212 ， 公式:rId214 具有不同的开口方向．\n则若 公式:rId189 ， 公式:rId191 两个方程具有相同的重根，设其为 公式:rId218 ，\n则 公式:rId212 ， 公式:rId214 对称轴为 公式:rId222 ，\n则 公式:rId224 对称轴为 公式:rId222 ，具有重根 公式:rId218 ，与 公式:rId193 有两个不同的实根矛盾，故 公式:rId189 ， 公式:rId191 两个方程具有不同的重根，设其分别为 公式:rId231 ，则\n公式:rId233\n公式:rId235 ，\n而等号由于 公式:rId237 ，不能同时取得，即 公式:rId239 无解，则 公式:rId241 无解．\n【点评 】 二次函数的问题，在天学网校强手营第二阶段的讲义中开辟了两个专门章节讲过，此题用到了二次函数的两根式，是讲义中原题改编。\n【难度】较高 。\n9 ． 设 公式:rId243 是等差数列，定义集合 公式:rId245 ，问： 公式:rId247 可以同时属于 M ？并证明你的结论．\n【解 析 】 A 集 合 中 元 素， 按照 适 当 顺 序 可 以 排 成 等 差数 列 ， 其 首 项 为 公式:rId249 ，末 项 为\n公式:rId251 ， 公 差与 原 数 列 公差 公式:rId253 一 样 ， 项 数为 3 1 ， 令 公式:rId255 ，则 公式:rId257 ，其中 公式:rId259 ，且 公式:rId261 显然 不 可 能 ．\n【评 论 】 不定方程和数列相结合的问题，在数论和数列章节中均有讲解．\n【难度】较高。\n10． 已知 公式:rId263 为正实数，且满足 公式:rId265 ，求证：\n公式:rId267 ．\n证明：\n（1）采用数学归纳法\n当 公式:rId269 时， 公式:rId271 ，不等式成立 ．\n假设当 公式:rId273 时不等式成立，考虑 公式:rId275 的情形：由于这 公式:rId277 个数不能同时都大于1，也不能同时都小于1，因此存在两个数，其中一个不大于1，另一个不小于1，不妨设为 公式:rId279 ，从而\n公式:rId281 ，\n公式:rId283\n公式:rId285\n公式:rId287\n公式:rId289 （1）\n公式:rId291\n公式:rId293\n其中推导（1）时利用 公式:rId295 及 公式:rId273 的假设，故 公式:rId275 时不等式也成立，\n综上，不等式对任意正整数 n 均成立 ．\n（2）采用均值不等式\n由均值不等式得\n公式:rId299\n公式:rId301\n两式相加得\n公式:rId303 ，\n公式:rId267 ．\n（3）用恒等展开\n左式展开得\n公式:rId306\n公式:rId308\n由均值不等式得\n公式:rId310\n公式:rId312\n故 公式:rId306\n公式:rId315\n公式:rId317 ．\n【点评】 此题在第一阶段和第二阶段的不等式一节中均有涉及，使用数学归纳法或者柯西不等式解题最佳。\n【难度】 较高\n证法一的难点在能否将 公式:rId277 个数中的两个数合并为“一个”，从而朝 公式:rId273 转化，此题 公式:rId279 ， 公式:rId322 是常见的不等式放缩 。 此证法也是“调整”基本思想的体现 。 证法二技巧性很高，但其实就是柯西不等式的证法之一 。 证法三恒等展开后用均值不等式放缩较易被想到，但难点为恒等展开。\n备考建议：\n1． 全国一本大纲，不分省命题，因此在复习时应补上没学过的内容；\n2． 文理科考查知识点基本相同，因此在复习时应该将在高考中文科不讲的知识点，如排列组合等补上；\n3． 侧重对于数学基本思想（如划归转化，分类讨论，数形结合，函数方程等思想）的考查，因此在复习时应注重对于数学的总体认知和思想性问题，尤其是对数学有深远影响的数学思想的学习；\n4． 考查内容宽泛，不考偏题怪题，但是考查难题，因此在复习时要采取与竞赛复习不同的策略，可以选取竞赛中的“好题”而不是偏题怪题；\n5． 高等数学的知识（如用导数判断函数的单调性、拉格朗日中值定理等）对于解题很有帮助，这也突出了自主招生考试不同于高考的重要区别，因此在复习时要学习一些高等数学的知 识。";
// paperData = "8  ．  已知实系数二次函数  公式:rId185  与  公式:rId187  满足方程  公式:rId189  和  公式:rId191  只有一对重根，已知  公式:rId193  有两个不同的实根，求证  公式:rId195  没有实根．\n【解  析  】若对于  公式:rId197  ，  公式:rId199  ，  公式:rId201  ，\n则有  公式:rId203  ，则  公式:rId205  最多有二等实根，\n同理若对于  公式:rId197  ，  公式:rId208  ，  公式:rId210  ，也会矛盾，故二次函数  公式:rId212  ，  公式:rId214  具有不同的开口方向．\n则若  公式:rId189  ，  公式:rId191  两个方程具有相同的重根，设其为  公式:rId218  ，\n则  公式:rId212  ，  公式:rId214  对称轴为  公式:rId222  ，\n则  公式:rId224  对称轴为  公式:rId222  ，具有重根  公式:rId218  ，与  公式:rId193  有两个不同的实根矛盾，故  公式:rId189  ，  公式:rId191  两个方程具有不同的重根，设其分别为  公式:rId231  ，则\n公式:rId233\n公式:rId235  ，\n而等号由于  公式:rId237  ，不能同时取得，即  公式:rId239  无解，则  公式:rId241  无解．\n【点评  】  二次函数的问题，在天学网校强手营第二阶段的讲义中开辟了两个专门章节讲过，此题用到了二次函数的两根式，是讲义中原题改编。\n【难度】较高  。\n\n";
