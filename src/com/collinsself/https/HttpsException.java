package com.collinsself.https;

public class HttpsException {
    public static class NetworkRequestException extends Exception {
        private final int responseCode;

        /**
         * @param message error message
         * @param responseCode HTTPs error code
         */
        public NetworkRequestException(String message, int responseCode) {
            super(message);
            this.responseCode = responseCode;
        }

        /**
         * @return responseCode from exception
         */
        public int getResponseCode() {
            return responseCode;
        }
    }
}
