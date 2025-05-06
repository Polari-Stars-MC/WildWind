template = ""
with open("template5.txt", encoding="utf8", mode='r') as f:
  for line in f:
    template = template + line

with open("1.csv", encoding="utf8", mode='r') as f:
  head = []
  for line in f:
    if len(head) == 0:
      head = line.strip().split(',')
    else:
      vals = line.strip().split(',')
      ans = template
      for i in range(len(head)):
        ans = ans.replace('${' + head[i] + '}', vals[i])
        if head[i] == 'name':
          title = vals[i].replace("_", " ").title()
          upper = vals[i].upper()
          ans = ans.replace('${Name}', title).replace('${NAME}', upper)
      print(ans)
