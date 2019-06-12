/*史长顺*/
package com.example.farming.entity;


import com.example.farming.constants.HttpStatus;

public class DataResult<T> {
    int status = HttpStatus.HTTP_STATUS_OK;
    T data;

    public int getStatus() {
        return status;
    }

    public DataResult<T> setStatus(int status) {
        this.status = status;
        return this;
    }

    public T getData() {
        return data;
    }

    public DataResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}
