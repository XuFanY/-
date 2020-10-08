import numpy as np
import pandas as pd
import os
import matplotlib.pyplot as plt

# 设置plt对象的底层参数
# 用来正常显示中文标签
plt.rcParams['font.sans-serif'] = ['SimHei']
# 用来正常显示负号
plt.rcParams['axes.unicode_minus'] = False

file=os.getcwd()+os.sep+'out'+os.sep+'cluster_result.csv'
data=pd.read_csv(file)

# 获取每个类别客户的相关数据
d1 = data.round(2).iloc[[0],[1,2,3,4,5,6]]
d2 = data.round(2).iloc[[1],[1,2,3,4,5,6]]
d3 = data.round(2).iloc[[2],[1,2,3,4,5,6]]
d4 = data.round(2).iloc[[3],[1,2,3,4,5,6]]
d5 = data.round(2).iloc[[4],[1,2,3,4,5,6]]

data1 = np.array(d1.values[0])
data2 = np.array(d2.values[0])
data3 = np.array(d3.values[0])
data4 = np.array(d4.values[0])
data5 = np.array(d5.values[0])

# 设置雷达图的轴标签
labels = np.array(['length','recency','frequency','mileages','coefficient','sum'])
# 设置雷达图的数据项个数
dataLength = 6
# 设置雷达图6个元素项的显示位置
angles = np.linspace(0, 2*np.pi, dataLength, endpoint = False)
# 设置雷达图的每个类别以闭合模式显示
data1 = np.concatenate((data1, [data1[0]]))
data2 = np.concatenate((data2, [data2[0]]))
data3 = np.concatenate((data3, [data3[0]]))
data4 = np.concatenate((data4, [data4[0]]))
data5 = np.concatenate((data5, [data5[0]]))
angles = np.concatenate((angles, [angles[0]]))

# 创建一个画布
fig = plt.figure()
ax = fig.add_subplot(111, polar=True)
# 绘制数据线（点线图）
ax.plot(angles, data1, 'bo--', linewidth=1, label='第00类客户')
ax.plot(angles, data2, 'ro--', linewidth=1, label='第01类客户')
ax.plot(angles, data3, 'go--', linewidth=1, label='第02类客户')
ax.plot(angles, data4, 'yo--', linewidth=1, label='第03类客户')
ax.plot(angles, data5, 'co--', linewidth=1, label='第04类客户')
# 设置标签角度
ax.set_thetagrids(angles * 180/np.pi, labels,)
# 设置图标题
ax.set_title('图：航空客户大数据分类特征值可视化', va='bottom')
# 设置取值范围
ax.set_rlim(-3,8)
# 显示背景网格
plt.grid(True)
# 设置图例显示
plt.legend(loc='best')
# 显示图表
plt.show()

'''
雷达图结论：
    基于模型：航空公司LRFMC模型, 其中：
        length - 会员入会时间距观测窗口结束的月数
        recency - 客户最近一次乘坐公司飞机距观测窗口结束的月数
        frequency - 客户在观测窗口内乘坐公司飞机的次数
        mileages - 客户在观测窗口内累计的飞行里程
        coefficient - 客户在观测窗口内乘坐舱位所对应的折扣系数的平均值
        sum - 客户总精英积分
    按照雷达图的显示，将客户分为五类“
        第00类客户：F、M、C、S 四个指标较高，R 指标较低：重要发展客户
        第01类客户：L 指标很低，其他指标一般：一般客户
        第02类客户：F、M、C、S 四个指标很高，R 指标很低:重要保持客户
        第03类客户：L 指标很高，其他指标一般：低价值客户
        第04类客户：，R 指标很高，F、M、C、S 四个指标较高: 重要挽留客户
'''


