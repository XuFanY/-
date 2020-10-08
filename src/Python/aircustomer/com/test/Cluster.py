import numpy as np
from sklearn.cluster import KMeans

import pandas as pd
import os

file=os.getcwd()+os.sep+'out'+os.sep+'data_reduction.csv'
data=pd.read_csv(file)

from sklearn import preprocessing
t= preprocessing.StandardScaler().fit(data)
data=t.transform(data)
print(data.mean(),data.std())

km = KMeans(n_clusters = 5)
km.fit(data)

labels = km.labels_
print('聚类后每个类别的标签为:{0}'.format(labels))

from sklearn.externals import joblib
joblib.dump(km, 'aircustomer.pkl')

print('11')