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
    *
    * git diff 比较暂存区域与 工作目录
    *
    * git diff xxxxx xxxxx 比较Git仓库两个快照
    * f5f6824 f5f4bfa
    *
    * 比较当前工作目录和Git仓库快照
    * git diff f5f4bfa
    *
    * 比较最新提交的快照和当前工作目录
    * git diff HEAD
    *
    * 比较最新提交的快照与暂存区域的文件
    * git diff --cached
    *
    * 比较快照与暂存区域的文件
    * git diff --cached f5f4bfa
    *
    * 修改最后一次提交
    * git commit --amend -m "新的提交说明"
    *
    * 将a.md 恢复到工作目录
    * git checkout -- a.md
    *
    * git rm a.md
    * 删除工作目录 和 暂存区域的文件 (取消跟踪，在下一次提交时不纳入版本管理)
    *
    * 暂存区域文件 与 工作目录文件不同时 rm命令
    * git rm -f a.md 可将两个文件都删除
    *
    * 只想删除暂存区域的文件 git rm --cached a.md
    *
    * 重命名文件
    * git mv a.md aa.md
    *
    * .gitignore 文件中可以记录 不想被Git跟踪的文件
    * */
}
