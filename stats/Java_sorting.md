
### Initial code to strip the fields with Regex
```
import re
import pandas as pd

data = pd.read_csv("java_sorting_24_7_17.txt", sep="|")
def filter_data(data):
    data.columns= [re.sub(r'\s+(\S+)\s+', r'\1', x) for x in data.columns]
    for i in range(1, len(data.columns)):
        try:
          data.iloc[:,i] = data.iloc[:,i].apply(lambda x: re.sub(r'\s+(\S+)\s+', r'\1', x))
        except Exception as e:
            print(e)
    data.loc[:, 'shuffle'] = data.loc[:, 'shuffle'].apply(lambda x: re.sub(r'\/(\d+)', r'\1',x))
    return data
data = filter_data(data)
```


```python
# Using strip to filter the values in the txt
import pandas as pd
import numpy as np
def read_stats(data_file):
    data = pd.read_csv(data_file, sep="|")
    data.columns = [ x.strip() for x in data.columns]

    # Filter integer indexes
    str_idxs = [idx for idx,dtype in zip(range(0,len(data.dtypes)), data.dtypes) if dtype != 'int64' ]

    # Strip fields
    for i in str_idxs:    
        key = data.columns[i]
        if data[key].dtype == np.dtype('str'):
            data.loc[:,key] = [ x.strip() for x in data.loc[:, key]]
    return data

data = read_stats("java_sorting_127.0.1.1_Di_1._Aug_07:39:03_UTC_2017.txt")
data.to_csv("java_sorting_127.0.1.1_Di_1._Aug_07:39:03_UTC_2017.csv")
```


```python
 [x for x in zip(range(0, len(data.columns)),data.columns)]   
```




    [(0, 'name'),
     (1, 'shuffle'),
     (2, 'elements'),
     (3, 'duration_ms'),
     (4, 'p_duration_s'),
     (5, 'p_duration_ns'),
     (6, 'memory')]




```python
import plotly
import plotly.plotly as py
import plotly.figure_factory as ff
from plotly.graph_objs import *
#plotly.offline.init_notebook_mode() 
def filter_by(data, name, value):
    data_length = len(data)
    return [idx for idx in range(0, data_length) if data.loc[idx,name] == value]

# using ~/.plotly/.credentials
# plotly.tools.set_credentials_file(username="", api_key="")

algorithms = set(data.loc[:, 'name'])
alg = algorithms.pop()
idxs = filter_by(data, 'name', alg)
X = data.loc[idxs, 'elements']
Y = data.loc[idxs, 'duration_ms']
plot_data = [Bar(x = X, y = Y, name=alg)]
layout = Layout(title= alg + ' performance (java) ',
                xaxis=dict(title='Elements'),
                yaxis=dict(title='Time'))
fig = Figure(data=plot_data, layout=layout)
py.iplot(fig)

```

[Plot](https://plot.ly/~martibayoalemany/154.embed)


```python
alg = algorithms.pop()
idxs = filter_by(data, 'name', alg)
X = data.loc[idxs, 'elements']
Y = data.loc[idxs, 'duration_ms']
plot_data = [Bar(x = X, y = Y, name=alg)]
layout = Layout(title= alg + ' performance (java) ',
                xaxis=dict(title='Elements'),
                yaxis=dict(title='Time'))
fig = Figure(data=plot_data, layout=layout)
py.iplot(fig)


```

[Plot](https://plot.ly/~martibayoalemany/156.embed)


```python
alg = algorithms.pop()
idxs = filter_by(data, 'name', alg)
X = data.loc[idxs, 'elements']
Y = data.loc[idxs, 'duration_ms']
plot_data = [Bar(x = X, y = Y, name=alg)]
layout = Layout(title= alg + ' performance (java) ',
                xaxis=dict(title='Elements'),
                yaxis=dict(title='Time'))
fig = Figure(data=plot_data, layout=layout)
py.iplot(fig)
```


[Plot](https://plot.ly/~martibayoalemany/158.embed)



```python
alg = algorithms.pop()
idxs = filter_by(data, 'name', alg)
X = data.loc[idxs, 'elements']
Y = data.loc[idxs, 'duration_ms']
plot_data = [Bar(x = X, y = Y, name=alg)]
layout = Layout(title= alg + ' performance (java) ',
                xaxis=dict(title='Elements'),
                yaxis=dict(title='Time'))
fig = Figure(data=plot_data, layout=layout)
py.iplot(fig)
```




<iframe id="igraph" scrolling="no" style="border:none;" seamless="seamless" src="https://plot.ly/~martibayoalemany/160.embed" height="525px" width="100%"></iframe>




```python
alg = algorithms.pop()
idxs = filter_by(data, 'name', alg)
X = data.loc[idxs, 'elements']
Y = data.loc[idxs, 'duration_ms']
plot_data = [Bar(x = X, y = Y, name=alg)]
layout = Layout(title= alg + ' performance (java) ',
                xaxis=dict(title='Elements'),
                yaxis=dict(title='Time'))
fig = Figure(data=plot_data, layout=layout)
py.iplot(fig)
```
[Plot](https://plot.ly/~martibayoalemany/162.embed)


## The same stats as before but with 9 Million data


```python
data2 = read_stats("java_sorting_127.0.1.1_Fr_4._Aug_23:59:33_UTC_2017.txt")
algorithms = set(data2.loc[:, 'name'])
alg = algorithms.pop()
idxs = filter_by(data2, 'name', alg)
X = data2.loc[idxs, 'elements']
Y = data2.loc[idxs, 'duration_ms']
plot_data = [Bar(x = X, y = Y, name=alg)]
layout = Layout(title= alg + ' performance (java) ',
                xaxis=dict(title='Elements'),
                yaxis=dict(title='Time'))
fig = Figure(data=plot_data, layout=layout)
py.iplot(fig)
```


```python
alg = algorithms.pop()
idxs = filter_by(data2, 'name', alg)
X = data2.loc[idxs, 'elements']
Y = data2.loc[idxs, 'duration_ms']
plot_data = [Bar(x = X, y = Y, name=alg)]
layout = Layout(title= alg + ' performance (java) ',
                xaxis=dict(title='Elements'),
                yaxis=dict(title='Time'))
fig = Figure(data=plot_data, layout=layout)
py.iplot(fig)
```

[Plot](https://plot.ly/~martibayoalemany/166.embed)


```python
alg = algorithms.pop()
idxs = filter_by(data2, 'name', alg)
X = data2.loc[idxs, 'elements']
Y = data2.loc[idxs, 'duration_ms']
plot_data = [Bar(x = X, y = Y, name=alg)]
layout = Layout(title= alg + ' performance (java) ',
                xaxis=dict(title='Elements'),
                yaxis=dict(title='Time'))
fig = Figure(data=plot_data, layout=layout)
py.iplot(fig)
```

[Plot](https://plot.ly/~martibayoalemany/168.embed)


```python
alg = algorithms.pop()
idxs = filter_by(data2, 'name', alg)
X = data2.loc[idxs, 'elements']
Y = data2.loc[idxs, 'duration_ms']
plot_data = [Bar(x = X, y = Y, name=alg)]
layout = Layout(title= alg + ' performance (java) ',
                xaxis=dict(title='Elements'),
                yaxis=dict(title='Time'))
fig = Figure(data=plot_data, layout=layout)
py.iplot(fig)
```



[Plot](https://plot.ly/~martibayoalemany/170.embed)



```python

```


```python

```
