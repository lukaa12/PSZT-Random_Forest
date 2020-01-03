import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns
from sklearn.datasets import load_boston
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_squared_error
from sklearn.model_selection import train_test_split

boston_dataset = load_boston()
boston = pd.DataFrame(boston_dataset.data, columns=boston_dataset.feature_names)
boston["MEDV"] = boston_dataset.target

print(boston.describe())
fig, axs = plt.subplots(ncols=7, nrows=2, figsize=(20, 10))
index = 0
axs = axs.flatten()
for col in boston.columns:
    sns.boxplot(y=col, data=boston[[col]], ax=axs[index])
    index += 1
plt.tight_layout(pad=0.4, w_pad=0.5, h_pad=5.0)
fname='tmp/stats.png'
plt.savefig(fname)

#dzielenie zbioru na treningowy i testowy
train_boston, test_boston = train_test_split(boston, test_size=0.2, random_state=4)
print(train_boston.shape)
print(test_boston.shape)
print()

x_label = "RM"  #cecha na podstawie, podstawie której zostanie narysowany wykres
plt.figure()
ax = sns.scatterplot(x=x_label, y="MEDV", data=train_boston[[x_label,"MEDV"]])
fname='tmp/rm_medv.png'
plt.savefig(fname)

# Lista nazw cech, których będziemy używać
features = ["RM", "CRIM", "ZN", "B"]
x_train, x_test = train_boston[features], test_boston[features]
y_train, y_test = train_boston["MEDV"], test_boston["MEDV"]

print(x_train.shape)
print(x_test.shape)
print(y_train.shape)
print(y_test.shape)

# Uzywamy gotowego modelu lasu losowego
rnd_for_model = RandomForestRegressor()
rnd_for_model.fit(x_train, y_train)

# liczymy błąd
y_train_predict = rnd_for_model.predict(x_train)
mse_train = mean_squared_error(y_train, y_train_predict)

print("Model performance on training data")
print(f"MSE: {mse_train}")
print()

y_test_predict = rnd_for_model.predict(x_test)
mse_test = mean_squared_error(y_test, y_test_predict)

print("Model performance on test data")
print(f"MSE: {mse_test}")


