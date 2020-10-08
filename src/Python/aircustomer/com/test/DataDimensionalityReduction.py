import pandas as pd
import os

file=os.getcwd()+os.sep+'out'+os.sep+'data_clean.csv'
data=pd.read_csv(file)

dataTemp=data[['MEMBER_NO','FFP_DATE','GENDER',
                'AGE','FLIGHT_COUNT','LAST_TO_END',
                'avg_discount','SEG_KM_SUM','EP_SUM']].copy()

length=[]
for value in dataTemp['FFP_DATE'] :
    ffpDate = value.split('/')
    year = int(ffpDate[0])
    month = int(ffpDate[1])
    l = (2014 - year) * 12 + (3 - month)
    length.append(l)
length=pd.Series(length)
recency = dataTemp['LAST_TO_END'] / 30
frequency = dataTemp['FLIGHT_COUNT']
mileages = dataTemp['SEG_KM_SUM']
coefficient = dataTemp['avg_discount']
sum = dataTemp['EP_SUM']


dataReduction=pd.concat([length,recency,frequency,mileages,coefficient,sum],axis=1)
dataReduction.columns = ['length','recency','frequency','mileages','coefficient','sum']
outPath=os.getcwd()+os.sep+'out'+os.sep+'data_reduction.csv'
dataReduction.to_csv(outPath, index=False)