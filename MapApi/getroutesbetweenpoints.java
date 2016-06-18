 public  void getpolylines(double lat1,double longi1,double lati2,double longi){



        String tag_json_obj = "json_obj_req";
        SharedPreferences sharedPreferences=getSharedPreferences("appdata",MODE_PRIVATE);

        String url = "https://maps.googleapis.com/maps/api/directions/json?origin="+lat1+","+longi1+"&destination="+lati2+","+longi+"&key=AIzaSyDiMfJzuVay2Fsp3dYnApBwsCgcZlDiiG0";

        System.out.println("url for road is=="+url);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{

                    System.out.println("Roadresponse=="+response);

                    JSONObject reader=new JSONObject(response);

                    JSONArray routes=reader.getJSONArray("routes");


                    for(int rou=0;rou<routes.length();rou++)
                    {

                        JSONArray legs=routes.getJSONObject(rou).getJSONArray("legs");
                        JSONArray stepsarray=legs.getJSONObject(0).getJSONArray("steps");
                        for(int i=0;i<stepsarray.length();i++)

                        {

                            System.out.println("stepsarray"+i);
                            String polyline=stepsarray.getJSONObject(i).getJSONObject("polyline").getString("points");
                            List<LatLng>list=    decodePoly(polyline);

                            for(int j=0;j<list.size();j++){

                                //     String prinntstring=""+list.get(i).latitude+""+list.get(i).longitude;
                                try{
                                    // Log.d("cordinates",prinntstring);
                                    mMap.addPolyline(new PolylineOptions()
                                            .add(list.get(j), list.get(j+1))
                                            .width(5)
                                            .color(Color.RED));
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                            }
                        }


                    }






                }catch (JSONException e){
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(),"Error in http request",Toast.LENGTH_SHORT).show();

                finish();

                if (volleyError instanceof TimeoutError) {

                }
            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
	
	
	 private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((double) lat / 1E5, (double) lng / 1E5);
            poly.add(p);
        }
        return poly;
    }
	