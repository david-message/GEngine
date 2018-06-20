console.info('log arg:%s',args[0])

def emp = ds.invoke('emp',args[0])
def kq = ds.invoke('kq',args[0])

console.info('log memo:%s',emp.memo)

def ts = emp.level*emp.salary

if(emp.age>25){
    ts*=1.1
}else{
    ts*=0.9
}

def bk = kq.get('补卡')
def jb = kq.get('加班')
def qj = kq.get('请假')

def t = jb+bk-qj

console.info('log total:%d',t)

if(t<100){ts=2000}

return ts;