import pandas as pd
import os


file=os.getcwd()+os.sep+'data'+os.sep+'air_data.csv'
data=pd.read_csv(file)
#print(data.info())

#初步删选原始数据，将第1年飞行累计和第2年飞行累计进行筛选过滤
condition=data['SUM_YR_1'].notnull()&data['SUM_YR_2'].notnull()
dataFirstClean=data[condition]
#print(dataFirstClean.shape)

#设置过滤条件再次筛选数据
condition1=dataFirstClean['SUM_YR_1']!=0
condition2=dataFirstClean['SUM_YR_2']!=0
condition3=(dataFirstClean['SEG_KM_SUM']!= 0)&(dataFirstClean['avg_discount']!= 0)

dataFinalClean=dataFirstClean[(condition1|condition2)&condition3]

outPath=os.getcwd()+os.sep+'out'+os.sep+'data_clean.csv'
dataFinalClean.to_csv(outPath)

