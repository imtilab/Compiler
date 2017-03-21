# Compiler: RegexEngine_RealNumber
<p>
Write a regular expression engine who only accepts real numbers.
<p>
- Real numbers could be positive or negative, fractional (float or double) or integers

Sample Input:
```
5
5.5
5.05
-5
-5.05
5.5.5
-a
5.-05
+5
```

Sample Output:
```
accept
accept
accept
accept
accept
reject
reject
reject
accept
```
