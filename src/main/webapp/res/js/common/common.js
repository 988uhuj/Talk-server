/**
 * Created by jfchen on 12/24/14.
 */

// 栈结构
function Stack()   //Creating Stack Object
{
    // Create an empty array of cards.
    this.cards = new Array();  //cards array inside stack object
    this.push  = pushData;      //Call pushdata function on push operation
    this.pop   = popData;        //Call popdata function on pop operation
    this.top = topData;
    this.printStack = showStackData; //Call showStackData function on printstack operation
}

function topData(){
    if(this.cards.length>0)
        return this.cards[this.cards.length-1];
    return null;
}
function pushData(data) {
    this.cards.push(data);
}

function popData(data) {
    return this.cards.pop();
}

function showStackData() {
    return this.cards;
}

//================================================

var g_tabStack= new Stack();
//关闭标签
$(".closeTheTab").click(function () {

    //there are multiple elements which has .closeTab icon so close the tab whose close icon is clicked
    var liId = $(this).parent().attr('id');
    //$(this).parent().parent().remove(); //remove li of tab
    //$('#myTab a:last').tab('show'); // Select first tab

    $(this).parent().parent().attr('class', 'hide');; //remove respective tab content

    var curTab = g_tabStack.pop();
    var lastTab = g_tabStack.pop();

    if(lastTab==liId){
        return;
    }

    var ele = $('#'+lastTab);
    showTab(lastTab);
});

function showTab(eleId){
    var ele = $("#"+eleId);
    ele.click();
}

function hideTab(eleId){
    var ele = $('#'+eleId);
    ele.parent().attr('class', 'hide');
}

function pushTabToStack(eleId){
    var topId=g_tabStack.top();
    if(eleId!=topId)
        g_tabStack.push(eleId);
}

function showdiv(id) {
    document.getElementById(id).style.display = "block";
}
function hidediv(id) {
    document.getElementById(id).style.display = "none";
}


