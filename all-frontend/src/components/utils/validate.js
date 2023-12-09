export function addToValidationsAnotherError(errorAll, validError) {
    if (errorAll && errorAll.validations) {
        errorAll.validations.push(validError);
    } else {
        errorAll = Object;
        errorAll.validations = [];
        errorAll.validations.push(validError);
    }
    return errorAll;
}


export function validateId(id) {
    return id !== null && id > 0;
}

export function validateName(name) {
    if (name === null || name === '') {
        return false;
    }
    return true;
}

export function validateCoordinates(coordinates) {
    if (coordinates === null) {
        return false;
    }
    if (coordinates.x === null || coordinates.y === null) {
        return false;
    }
    if (!isNaN(coordinates.x) && coordinates.x !== '') {
        if (!Number.isInteger(coordinates.y)) {
            return false;
        }
    } else {
        return false;
    }

    return true;
}

export function validateCreationDate(dateString) {
    // Паттерн для даты в формате "YYYY-MM-DD"
    const datePattern = /^\d{4}-\d{2}-\d{2}$/;

    // Проверяем, соответствует ли строка паттерну
    if (datePattern.test(dateString)) {
        const parts = dateString.split("-");
        const year = parseInt(parts[0], 10);
        const month = parseInt(parts[1], 10);
        const day = parseInt(parts[2], 10);

        if (month >= 1 && month <= 12 && day >= 1 && day <= 31) {
            if (month === 2) {
                const isLeapYear = (year % 4 === 0 && year % 100 !== 0) || (year % 400 === 0);
                if (day <= 29 || (day === 29 && isLeapYear)) {
                    return true;
                }
            } else if (month === 4 || month === 6 || month === 9 || month === 11) {
                if (day <= 30) {
                    return true;
                }
            } else {
                return true;
            }
        }
    }

    return false;
}

export function validateAnnualTurnover(annualTurnover) {
    if (annualTurnover === null) {
        return false;
    }
    if (!isNaN(annualTurnover) && annualTurnover !== '') {
    } else {
        return false;
    }
    if (annualTurnover <= 0) {
        return false;
    }
    return true;
}


export function validatePageNumberOrPageSize(pageCount) {
    if (pageCount === '') {
        return false;
    }
    return pageCount >= 0;
}

export function validateOrganizationFilters(filters) {
    for (let filter of filters) {
        if (filter === null || filter === '' || filter.trim() === '') {
            return false;
        }
    }

    return true;
}
