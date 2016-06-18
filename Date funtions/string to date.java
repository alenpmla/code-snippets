 private Date stringToDate(String aDate, String aFormat) {

        if(aDate==null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;

    } 
	
Show userfriendly date and time

	
	PrettyTime p=new PrettyTime();
	
	p.format(date);
	
	   compile 'org.ocpsoft.prettytime:prettytime:4.0.1.Final'
	   
	   
	   repositories {
        maven { url 'https://oss.sonatype.org/content/repositories/snapshots/' }
    }