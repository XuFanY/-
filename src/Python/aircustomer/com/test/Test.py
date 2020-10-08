import numpy as np
from sklearn.cluster import KMeans

import pandas as pd
import os



from sklearn.externals import joblib
km_joblib = joblib.load('aircustomer.pkl')

r1 = pd.Series(km_joblib.labels_).value_counts()
print('统计每个类别的样本数量：\n{0}'.format(r1))

r2 = pd.DataFrame(km_joblib.cluster_centers_)
print('统计每个类别的聚类中心数据:\n{0}'.format(r2))

file=os.getcwd()+os.sep+'out'+os.sep+'data_reduction.csv'
data=pd.read_csv(file)

r = pd.concat([r1, r2], axis=1)
r.columns = ['聚类个数'] + list(data.columns)
print('KMeans聚类分析统计数据:\n{0}'.format(r))
outPath=os.getcwd()+os.sep+'out'+os.sep+'cluster_result.csv'
r.to_csv(outPath, index=False)

print('11')