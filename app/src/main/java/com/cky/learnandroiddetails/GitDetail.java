package com.cky.learnandroiddetails;

/**
 * Created by cuikangyuan on 16/7/17.
 */
public class GitDetail {

    /*
    * 1.
    * git config --global user.name "xxx"
    *
    * git config --global user.email "xxx"
    *
    * git config --list
    *
    * 工作目录 暂存区域 Git仓库
    *
    * 在工作目录中添加 修改文件
    * 将需要进行版本控制的文件放入暂存区域
    * 将暂存区域的文件提交到Git仓库
    *
    * git init
    *
    * git add
    *
    * git commit -m
    *
    * git reset HEAD //恢复暂存区域
    *
    * git checkout -- xxx discard changes in working directory
    *
    * git log
    *
    * 默认 --mixed
    * git reset --mixed HEAD~ 回到上一个版本
    * 移动HEAD的指向 将其指向上一个快照
    * 将HEAD移动后指向的快照回滚到暂存区域
    *
    * git reset --mixed HEAD~2
    *
    *
    * git reset --soft HEAD~
    * 移动HEAD的指向 将其指向上一个快照 撤销一次提交
    *
    * git reset --hard HEAD~
    * 移动HEAD的指向 将其指向上一个快照
    * 将HEAD移动后指向的快照回滚到暂存区域
    * 将暂存区域的文件还原到工作目录
    *
    * 回滚指定快照
    *
    * 回滚快照个别文件 HEAD指针不会移动
    *
    * git reflog Git记录下的每一次操作
    * */
}
