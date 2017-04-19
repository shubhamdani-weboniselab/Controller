package com.webonise.controller.search;

import java.util.List;

class UserModel {

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
        private int Page;
        private int PageSize;
        private int Total;
        private List<UsersEntity> Users;

        public int getPage() {
            return Page;
        }

        public void setPage(int Page) {
            this.Page = Page;
        }

        public int getPageSize() {
            return PageSize;
        }

        public void setPageSize(int PageSize) {
            this.PageSize = PageSize;
        }

        public int getTotal() {
            return Total;
        }

        public void setTotal(int Total) {
            this.Total = Total;
        }

        public List<UsersEntity> getUsers() {
            return Users;
        }

        public void setUsers(List<UsersEntity> Users) {
            this.Users = Users;
        }

        public static class UsersEntity {
            private String ContactNumber;
            private String CustomAttribute1;
            private String Email;
            private String EmailUserName;
            private String EnrolledDevicesCount;
            private String ExternalId;
            private String FirstName;
            private String Group;
            private IdEntity Id;
            private String LastName;
            private String LocationGroupId;
            private int MessageType;
            private String MobileNumber;
            private String Role;
            private int SecurityType;
            private boolean Status;
            private String UserName;

            public String getContactNumber() {
                return ContactNumber;
            }

            public void setContactNumber(String ContactNumber) {
                this.ContactNumber = ContactNumber;
            }

            public String getCustomAttribute1() {
                return CustomAttribute1;
            }

            public void setCustomAttribute1(String CustomAttribute1) {
                this.CustomAttribute1 = CustomAttribute1;
            }

            public String getEmail() {
                return Email;
            }

            public void setEmail(String Email) {
                this.Email = Email;
            }

            public String getEmailUserName() {
                return EmailUserName;
            }

            public void setEmailUserName(String EmailUserName) {
                this.EmailUserName = EmailUserName;
            }

            public String getEnrolledDevicesCount() {
                return EnrolledDevicesCount;
            }

            public void setEnrolledDevicesCount(String EnrolledDevicesCount) {
                this.EnrolledDevicesCount = EnrolledDevicesCount;
            }

            public String getExternalId() {
                return ExternalId;
            }

            public void setExternalId(String ExternalId) {
                this.ExternalId = ExternalId;
            }

            public String getFirstName() {
                return FirstName;
            }

            public void setFirstName(String FirstName) {
                this.FirstName = FirstName;
            }

            public String getGroup() {
                return Group;
            }

            public void setGroup(String Group) {
                this.Group = Group;
            }

            public IdEntity getId() {
                return Id;
            }

            public void setId(IdEntity Id) {
                this.Id = Id;
            }

            public String getLastName() {
                return LastName;
            }

            public void setLastName(String LastName) {
                this.LastName = LastName;
            }

            public String getLocationGroupId() {
                return LocationGroupId;
            }

            public void setLocationGroupId(String LocationGroupId) {
                this.LocationGroupId = LocationGroupId;
            }

            public int getMessageType() {
                return MessageType;
            }

            public void setMessageType(int MessageType) {
                this.MessageType = MessageType;
            }

            public String getMobileNumber() {
                return MobileNumber;
            }

            public void setMobileNumber(String MobileNumber) {
                this.MobileNumber = MobileNumber;
            }

            public String getRole() {
                return Role;
            }

            public void setRole(String Role) {
                this.Role = Role;
            }

            public int getSecurityType() {
                return SecurityType;
            }

            public void setSecurityType(int SecurityType) {
                this.SecurityType = SecurityType;
            }

            public boolean isStatus() {
                return Status;
            }

            public void setStatus(boolean Status) {
                this.Status = Status;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public static class IdEntity {
                private int Value;

                public int getValue() {
                    return Value;
                }

                public void setValue(int Value) {
                    this.Value = Value;
                }
            }
        }
    }
}
