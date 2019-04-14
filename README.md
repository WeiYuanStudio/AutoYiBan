# AutoYiBan 自动化易班刷评论

## 发话题

通过抓包发现发话题时POST请求如下


Request URL：http://www.yiban.cn/forum/article/addAjax

```
Form Data
---
puid: //该项为公众帐号ID
pubArea: //该项为发布空间ID，据目前观察是在发布时选择“我加入的群决定的”，如果选择发送公开至机构号，那么该项为0
title: //发送话题的标题
content: <p>Hello World<br/></p> //发送的话题内容，是HTML格式发送，发送时应该会encode，至于这里能不能发送一些脚本还未测试，感觉会有空子可钻
isNotice: false //应该是是否设置为通知吧，个人账户大概无法查看该选项
dom: .js-submit
```

## 发投票

通过抓包

Request URL：http://www.yiban.cn/vote/vote/add

```
Form Data
---
puid: //该项为公众帐号ID
scope_ids: 749376
title: //投票的标题
subjectTxt: text
subjectPic: //图片，可空
options_num: 3 //三个选项
scopeMin: 1
scopeMax: 1
minimum: 1
voteValue: 2019-04-16 13:34
voteKey: 2
public_type: 0 
isAnonymous: 1 //是否匿名（匿名为2，不匿名1）
voteIsCaptcha: 0 //是否要求验证码
istop: 1
sysnotice: 2
isshare: 1
rsa: 1
dom: .js-submit
group_id: 749376
subjectTxt_1: 1 //投票选项1的内容
subjectTxt_2: 2 //投票选项2的内容
subjectTxt_3: 3 //投票选项3的内容
```

## 为话题点赞

```
Form
---
article_id: 72372649
channel_id: 433068
puid: 8323797
```

## 投票

```
Form
---
puid: //该项为公众帐号ID
group_id: 648688
vote_id: //投票号
actor_id: //执行人用户ID 即是user_ID
voptions_id: //投票选择项ID，头疼，又要爬取页面，就不能直接1，2，3吗
minimum: 1 //应该是最小投票数
scopeMax: 1 //和最大投票数
```

## 点赞投票

Request URL: http://www.yiban.cn/vote/vote/editLove

```
Form
---
puid: 8323797 //该项为公众帐号ID
group_id: 648688 
vote_id: 48411265 //投票号
actor_id: 18235066 //执行人用户ID 即是user_ID
flag: 1 //1点赞，2取消赞
```

## 回复投票

```
Form
---
mountid: 66669146
msg: //回复消息内容
group_id: 648688
actor_id: 18235066 //执行人用户ID 即是user_ID
vote_id: 48323279
author_id: 18683756 //投票项目发起人ID
puid: 8323797 //公众帐号ID
reply_comment_id: //回复投票下的评论的评论ID
reply_user_id: //被回复评论的用户的user_ID
```