# vscode debugging

1. install the microsoft c# extension
2. run the command `.NET: Generate Assets for Build and Debug `
3. open the newly generated launch.json and set:

```json
"args": ["<day number>"],
"cwd": "${workspaceFolder}/csharp",
```
