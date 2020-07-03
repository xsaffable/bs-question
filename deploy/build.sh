#!/bin/bash
#如需使用其他版本的jdk请联系scm，打开并修改下面的变量
#export JAVA_HOME=xxx (如使用系统默认的不需要设置，系统默认版本1.7.0)
#export PATH=$JAVA_HOME/bin:$PATH
#
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home

if [ ! -d "$JAVA_HOME" ]; then
  echo "not set JAVA_HOME in Environment!"
  export JAVA_HOME=/usr/local/jdk1.8.0_65 #(使用jdk8请设置)
  export PATH=$JAVA_HOME/bin:$PATH
fi

#
#如需使用其他版本的maven请联系scm，打开并修改下面的变量
#export MAVEN_HOME=xxxx (如使用系统默认的不需要设置，默认maven-3.3.3)
#export PATH=$MAVEN_HOME/bin:$PATH
#
#######start#######
cd ..
mvn clean package -Dmaven.test.skip=true -Pprod
ret=$?
if [ $ret -ne 0 ];then
    echo "===== maven build failure ====="
    exit $ret
else
    echo -n "===== maven build successfully! ====="
fi
cd deploy
rm -rf output
mkdir output
cp control.sh output/ # 拷贝control.sh脚本 至output目录下
unzip -d output/ ../target/gjxx-web*.zip #将zip包解压到output目录下