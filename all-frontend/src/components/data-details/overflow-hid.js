let tooltipElem;

document.onmouseover = function(event) {
    let target = event.target;

    if (target.scrollWidth > 355) {
        // если у нас есть подсказка...
        let tooltipHtml = target.dataset.tooltip;
        if (!tooltipHtml) return;

        // ...создадим элемент для подсказки

        tooltipElem = document.createElement('div');
        tooltipElem.className = 'tooltip';
        tooltipElem.innerHTML = tooltipHtml;
        document.body.append(tooltipElem);

        // спозиционируем его сверху от аннотируемого элемента (top-center)
        let coords = target.getBoundingClientRect();

        let left = coords.left + (target.offsetWidth - tooltipElem.offsetWidth) * 0;
        if (left < 0) left = 0; // не заезжать за левый край окна

        let top = coords.top + tooltipElem.offsetHeight + 35;
        // if (top < 0) { // если подсказка не помещается сверху, то отображать её снизу
        //     top = coords.top + target.offsetHeight + 15;
        // }

        tooltipElem.style.position = "fixed";
        tooltipElem.style.zIndex = "1000";
        tooltipElem.style.padding = "10px 20px";
        tooltipElem.style.border = "1px solid #b3c9ce";
        tooltipElem.style.borderRadius = "4px";
        tooltipElem.style.textAlign = "left";
        tooltipElem.style.wordWrap = "break-word";
        tooltipElem.style.wordBreak = "break-all";
        tooltipElem.style.font = "italic 14px/1.3 sans-serif";
        tooltipElem.style.color = "#333";
        tooltipElem.style.backgroundColor = "#ffffff";
        tooltipElem.style.boxShadow = "3px 3px 3px rgba(0, 0, 0, .3)";
        tooltipElem.style.left = left + 'px';
        tooltipElem.style.top = top + 'px';
    }
};

document.onmouseout = function(e) {

    if (tooltipElem) {
        tooltipElem.remove();
        tooltipElem = null;
    }

};