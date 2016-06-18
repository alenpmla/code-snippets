  GpsTracker gps = new GpsTracker(MainActivity.this);

        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            

        }else
        {

            gps.showSettingsAlert();
        }