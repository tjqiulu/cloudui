// -------------------------------------------------------------------------
// Copyright (c) 2006-2017 GEMALTO group. All Rights Reserved.
//
// This software is the confidential and proprietary information of
// GEMALTO.
//
// Project name:
//
// Platform : Java virtual machine
// Language : JAVA 5.0
//
// Original author: guhuang
//
// -------------------------------------------------------------------------
// GEMALTO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
// THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
// TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
// PARTICULAR PURPOSE, OR NON-INFRINGEMENT. GEMALTO SHALL NOT BE
// LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
// MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
//
// THIS SOFTWARE IS NOT DESIGNED OR INTENDED FOR USE OR RESALE AS ON-LINE
// CONTROL EQUIPMENT IN HAZARDOUS ENVIRONMENTS REQUIRING FAIL-SAFE
// PERFORMANCE, SUCH AS IN THE OPERATION OF NUCLEAR FACILITIES, AIRCRAFT
// NAVIGATION OR COMMUNICATION SYSTEMS, AIR TRAFFIC CONTROL, DIRECT LIFE
// SUPPORT MACHINES, OR WEAPONS SYSTEMS, IN WHICH THE FAILURE OF THE
// SOFTWARE COULD LEAD DIRECTLY TO DEATH, PERSONAL INJURY, OR SEVERE
// PHYSICAL OR ENVIRONMENTAL DAMAGE ("HIGH RISK ACTIVITIES"). GEMALTO
// SPECIFICALLY DISCLAIMS ANY EXPRESS OR IMPLIED WARRANTY OF FITNESS FOR
// HIGH RISK ACTIVITIES.
// -------------------------------------------------------------------------

package com.gemalto.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Class: ParserPinUtil
 * <p>
 * Purpose: TODO
 * </p>
 * 
 * @author guhuang
 * @version 1.0
 * @created Dec 23, 2017 8:58:18 PM
 */
public class ParserPinUtil {

    /**
     * The logger for trace
     */
    private final static Logger LOG = LoggerFactory.getLogger (ParserPinUtil.class.getName ());

    private final static int DEFAULT_TOTAL_WIDTH = 1080;

    private final static int DEFAULT_TOTAL_HIGH = 784;

    private final static int DEFAULT_CELL_X_NUMBER = 3;

    private final static int DEFAULT_CELL_Y_NUMBER = 4;

    private final static int DIRECTION_EAST_SOUTH = 1;

    private final static int DIRECTION_EAST_NORTH = 2;

    // private final static String DIRECTION_WEST_SOUTH = "WEST_SOUTH";
    //
    // private final static String DIRECTION_WEST_NORTH = "WEST_NORTH";

    private final static int DEFAULT_DIRECTION = DIRECTION_EAST_SOUTH;


    public static int parsePosition2Number (final String picFileName, final int xPixel,
            final int yPixel) throws Exception {
        return parsePosition2Number (picFileName, xPixel, yPixel, DEFAULT_TOTAL_WIDTH,
                DEFAULT_TOTAL_HIGH, DEFAULT_CELL_X_NUMBER, DEFAULT_CELL_Y_NUMBER, DEFAULT_DIRECTION);
    }


    public static int parsePosition2Number (final String picFileName, final int xPixel,
            final int yPixel, final int nTotalWidth, final int nTotalHigh, final int nCellNumberX,
            final int nCellNumberY, final int direction) throws Exception {
        final int nCellWidth = nTotalWidth / nCellNumberX;
        final int nCellHigh = nTotalHigh / nCellNumberY;

        final int rowLocation = yPixel / nCellHigh;
        final int columnLocation = xPixel / nCellWidth;

        String sLocationSequence = "";
        if (direction == DIRECTION_EAST_SOUTH) {
            sLocationSequence = picFileName.substring (0, picFileName.indexOf ("."));
        } else if (direction == DIRECTION_EAST_NORTH) {
            final String sName = picFileName.substring (0, picFileName.indexOf ("."));
            final String part1 = sName.substring (sName.length () - nCellNumberX * 1,
                    sName.length ());
            final String part2 = sName.substring (sName.length () - nCellNumberX * 2,
                    sName.length () - nCellNumberX * 1);
            final String part3 = sName.substring (sName.length () - nCellNumberX * 3,
                    sName.length () - nCellNumberX * 2);
            final String part4 = sName.substring (sName.length () - nCellNumberX * 4,
                    sName.length () - nCellNumberX * 3);
            sLocationSequence = part1 + part2 + part3 + part4;
        }

        return getNumberFromLocation (sLocationSequence, nCellNumberX, rowLocation, columnLocation);
    }


    public static int getNumberFromLocation (final String sLocationSequence,
            final int nCellNumberX, final int rowIndex, final int colIndex) throws Exception {
        int numberResult = 0;
        final int index = rowIndex * nCellNumberX + colIndex;
        final String result = sLocationSequence.substring (index, index + 1);
        if (result.matches ("\\d{1}")) {
            numberResult = Integer.parseInt (result);
        } else {
            throw new Exception ("Wrong Location without Number click!");
        }

        return numberResult;

    }


    /**
     * Method documentation to be filled TODO
     * 
     * @param args
     */
    public static void main (final String[] args) {

        final int xPix = 543;
        final int yPix = 718;
        int numberResult;
        try {
            numberResult = ParserPinUtil.parsePosition2Number ("257390146x8x.png", xPix, yPix);
            System.out.println ("Current Number is : " + numberResult);
        } catch (final Exception e) {
            System.out.println ("Exception: " + e.getMessage ());
        }

    }

}
