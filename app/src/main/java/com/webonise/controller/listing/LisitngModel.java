package com.webonise.controller.listing;

import java.util.List;

public class LisitngModel {
    private DataEntity data;
    private String msg;
    private int status;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataEntity {
        private int count;
        private List<ListEntity> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public static class ListEntity {
            private FieldsEntity fields;
            private String guid;
            private String type;

            public FieldsEntity getFields() {
                return fields;
            }

            public void setFields(FieldsEntity fields) {
                this.fields = fields;
            }

            public String getGuid() {
                return guid;
            }

            public void setGuid(String guid) {
                this.guid = guid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public static class FieldsEntity {
                private boolean checked;
                private String name;

                public boolean isChecked() {
                    return checked;
                }

                public void setChecked(boolean isChecked) {
                    this.checked = isChecked;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
