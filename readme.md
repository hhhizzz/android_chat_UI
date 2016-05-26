##android_chat UI
A light android chat UI, something like Allo
![](https://git.oschina.net/xunix/android_chat/raw/master/sample.png?dir=0&filepath=sample.png&oid=103f2d770fc30679408d56c99ac6ef1a836cfbc0&sha=beb1481109fa6fb3fec39a6f0e5191cffb844991)

##How to use
`build.grade`

```xml
    compile project(":android_chat")
```
`MainActivity.java`

```java
public class MainActivity extends ChatActivity{
    private static final Integer myMessage=1;
    private static final Integer yourMessage=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Pikie Pie");
        sendMessage(new Bean("hello,my name is Pikie Pie",yourMessage));
        sendMessage(new Bean("hello,my name is Apple Jack",myMessage));
    }
}
```
##Notice
if you want to know more about the bubble,click here
[lguipeng/BubbleView](https://github.com/lguipeng/BubbleView)
##License
```
Copyright 2016 XunixH

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
