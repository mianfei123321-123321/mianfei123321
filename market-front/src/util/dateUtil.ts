function formatStr(num:number) {
  return num < 10 ? "0" + num:num;
}
export function now():string {
  let date = new Date();
  let fullYear = date.getFullYear();
  let month = formatStr(date.getMonth()+1);// 0-11
  let day = formatStr(date.getDate());// 月的天数
  let hours = formatStr(date.getHours());
  let minutes = formatStr(date.getMinutes());
  let seconds = formatStr(date.getSeconds());
  return fullYear +"-" + month + "-" + day +" "+hours+":"+minutes+":"+seconds;
}
