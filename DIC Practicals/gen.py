var = [0] * 19
var[18] = 8
for i in range(17, -1, -1):
    var[i] = var[i + 1]/2

print(var)