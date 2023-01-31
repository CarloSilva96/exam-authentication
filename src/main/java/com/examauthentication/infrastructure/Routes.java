package com.examauthentication.infrastructure;

public final class Routes {
    public static final class ExamAuth {
        public static final String ROOT = "exam-auth";
        public static final String V1 = ROOT + "/v1";

        public static final class Task {
            public static final String PATH = ExamAuth.V1 + "/tasks";
        }

        public static final class User {
            public static final String PATH = ExamAuth.V1 + "/users";
        }
    }
}
