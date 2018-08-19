### login [body]
## request: client -> server
```$xslt
{
"uid": 12,
"username": "daxiong"
"token": "fdsjafdsjalfjslafjdsajf"  // token: username + "_" + ssoKey + "_" + timestamp
                                    // ssokey: 约定key
}
```
## response: server -> client
```$xslt
""
```