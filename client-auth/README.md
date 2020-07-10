# 统一资源认证服务中心 Spring Security + oauth2 + jwt

## 项目结构
```
config.mybatis
config.oauth
    OAuth2Config            -- 认证配置
    ResourceServerConfig    -- 资源服务
    WebSecurityConfig
security
    CustomAuthenticationProvider    -- password认证
    CodeAuthenticationProvider      -- code认证
    CustomTokenEnhancer             -- Jwt token 处理
    CustomRedisTokenStore           -- redis缓存token
    CustomAuthenticationToken       -- 自定义token
    CustomAuthorizationTokenServices-- token service
    CustomUserDetails               -- token中带的信息
```

## 认证方式

### password认证

```
-- 获取token
post http://localhost:10101/oauth/token?grant_type=password
Authorization:Basic ZnJvbnRlbmQ6ZnJvbnRlbmQ=
username:xxx
password:xxx
client:xxx

result:
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODM5NzEyMTksIlgtQU9ITy1Vc2VySWQiOiIwMDEiLCJ1c2VyX25hbWUiOiIwMDEiLCJqdGkiOiI4NDEzNTY3NS02YTM2LTQxNGYtOTllNi03OWYwYWIzYmU5MjAiLCJjbGllbnRfaWQiOiJmcm9udGVuZCIsInNjb3BlIjpbImFsbCJdfQ.UhjyM-JvCbXzv4NETQjkBM0BmRaAiXKY8W2ePN_9WwM",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiIwMDEiLCJzY29wZSI6WyJhbGwiXSwiYXRpIjoiODQxMzU2NzUtNmEzNi00MTRmLTk5ZTYtNzlmMGFiM2JlOTIwIiwiZXhwIjoxNTg2NTIwMDE5LCJYLUFPSE8tVXNlcklkIjoiMDAxIiwianRpIjoiNDgzMmY1ZWItODBmYi00N2FkLWI4NDgtZGRjM2RkYzY0ZDliIiwiY2xpZW50X2lkIjoiZnJvbnRlbmQifQ.FSZ75vDGecg5aAeVCFpsCZ98LUMivoTrXsSdW8kOtUw",
    "expires_in": 43199,
    "scope": "all",
    "X-AOHO-UserId": "001",
    "jti": "84135675-6a36-414f-99e6-79f0ab3be920",
    "X-AOHO-ClientId": "db123457"
}

-- 检查token
post http://localhost:10101/oauth/check_token?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODQwMjM2MTYsIlgtQU9ITy1Vc2VySWQiOiIwMDEiLCJ1c2VyX25hbWUiOiIwMDEiLCJqdGkiOiI5OWEyMjljMS05MTQyLTQ4NDktOGNlOS0xNGU0NjFlZDJlNmIiLCJjbGllbnRfaWQiOiJmcm9udGVuZCIsInNjb3BlIjpbImFsbCJdfQ.4U2tKWB1VL2vII68gP7dg52QMTgbm4-umY9OYpNPDC8
Authorization:Basic ZnJvbnRlbmQ6ZnJvbnRlbmQ=

result:
{
    "user_name": "001",
    "scope": [
        "all"
    ],
    "active": true,
    "X-AOHO-ClientId": "db123457",
    "exp": 1584023616,
    "X-AOHO-UserId": "001",
    "jti": "99a229c1-9142-4849-8ce9-14e461ed2e6b",
    "client_id": "frontend"
}

-- 刷新token
post http://localhost:10101/oauth/token?grant_type=refresh_token&refresh_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiIwMDEiLCJzY29wZSI6WyJhbGwiXSwiYXRpIjoiMmZhOWMzYTMtYzY4Yy00Y2MwLTliYWQtZjljZWZiM2E0ZjU5IiwiZXhwIjoxNTg2NTc1NzM3LCJYLUFPSE8tVXNlcklkIjoiMDAxIiwianRpIjoiMmM3MDI2MGYtZjFhMS00MzRjLTg3YmEtZGNmNjcxZGQyN2E2IiwiY2xpZW50X2lkIjoiZnJvbnRlbmQifQ.3BkPj7kjY5Oxi5_ydpfkMeORNw801jM92uZiTx0_XBo
Authorization:Basic ZnJvbnRlbmQ6ZnJvbnRlbmQ=

result:
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODQwMjY5NzQsIlgtQU9ITy1Vc2VySWQiOiIwMDEiLCJ1c2VyX25hbWUiOiIwMDEiLCJqdGkiOiJkNDZjYmJkOC1kMTE0LTRmOGEtYTFjYS01MjE4OTY0MzM1NjQiLCJjbGllbnRfaWQiOiJmcm9udGVuZCIsInNjb3BlIjpbImFsbCJdfQ.yUtSypB3I7YD1zuCGjFj4Lnm-lJvz6TgNzcyRC3Y9wE",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiIwMDEiLCJzY29wZSI6WyJhbGwiXSwiYXRpIjoiZDQ2Y2JiZDgtZDExNC00ZjhhLWExY2EtNTIxODk2NDMzNTY0IiwiZXhwIjoxNTg2NTc1Nzc0LCJYLUFPSE8tVXNlcklkIjoiMDAxIiwianRpIjoiNDg5NDUxM2EtZTljMy00MmU2LWFkYzYtMDYwNjRiYzhjMTQ2IiwiY2xpZW50X2lkIjoiZnJvbnRlbmQifQ.amAZZZmo-u6XBIdHGoYmBcCyCOaI49cYk_b8kKve0uM",
    "expires_in": 43199,
    "scope": "all",
    "X-AOHO-UserId": "001",
    "jti": "d46cbbd8-d114-4f8a-a1ca-521896433564",
    "X-AOHO-ClientId": "db123457"
}
```

### code认证

